from flask import Flask, jsonify, send_from_directory,render_template,request
from flask_cors import CORS
import pyodbc
import mysql.connector
import time
from datetime import datetime

app = Flask(__name__, static_folder="static", static_url_path="/")
CORS(app)

# MSSQL Connection Config
MSSQL_CONFIG = {
    "server": "113.23.136.34",
    "database": "TESTINGDB",
    "username": "user_viewtestingdb",
    "password": "user_viewtestingdb#131024$^~"
}

# MySQL Connection Config
MYSQL_CONFIG = {
    "host": "localhost",
    "user": "root",
    "password": "",
    "database": "kptrtdb"
}

# Function to connect to MSSQL
def connect_mssql():
    conn_str = f"DRIVER={{SQL Server}};SERVER={MSSQL_CONFIG['server']};DATABASE={MSSQL_CONFIG['database']};UID={MSSQL_CONFIG['username']};PWD={MSSQL_CONFIG['password']}"
    return pyodbc.connect(conn_str)

# Function to connect to MySQL
def connect_mysql():
    return mysql.connector.connect(**MYSQL_CONFIG)

# Serve index.html
@app.route("/")
def serve_index():
    return send_from_directory("static", "index.html")

# Show Data from MySQL Table with Pagination and Search by Matric Number
@app.route("/show_data", methods=["GET"])
def show_data():
    try:
        page = int(request.args.get("page", 1))  # Default to page 1 if not specified
        per_page = 500  # Number of records per page
        offset = (page - 1) * per_page
        search_query = request.args.get("search", "")
        
        mysql_conn = connect_mysql()
        mysql_cursor = mysql_conn.cursor(dictionary=True)
        
        # Search logic (only by matric number)
        if search_query:
            query = "SELECT * FROM D1_Student_Realtime WHERE matrik_no LIKE %s LIMIT %s OFFSET %s"
            params = (f"%{search_query}%", per_page, offset)
        else:
            query = "SELECT * FROM D1_Student_Realtime LIMIT %s OFFSET %s"
            params = (per_page, offset)
        
        mysql_cursor.execute(query, params)
        records = mysql_cursor.fetchall()
        
        # Add row numbers
        start_number = offset + 1
        for index, record in enumerate(records):
            record["row_number"] = start_number + index

        # Get total count of records for pagination
        if search_query:
            mysql_cursor.execute("SELECT COUNT(*) as total FROM D1_Student_Realtime WHERE matrik_no LIKE %s", (f"%{search_query}%",))
        else:
            mysql_cursor.execute("SELECT COUNT(*) as total FROM D1_Student_Realtime")
        total_records = mysql_cursor.fetchone()["total"]
        total_pages = (total_records + per_page - 1) // per_page

        mysql_cursor.close()
        mysql_conn.close()

        return render_template("show.html", records=records, page=page, total_pages=total_pages, search_query=search_query)
    except Exception as e:
        print("Error:", str(e))
        return jsonify({"error": str(e)}), 500
    
# Migration Route
@app.route("/migrate", methods=["GET"])
def migrate_data():
    start_time = time.time()
    total_migrated = 0

    try:
        # Connect to MSSQL
        mssql_conn = connect_mssql()
        mssql_cursor = mssql_conn.cursor()
        mssql_cursor.execute("SELECT * FROM [Student].[D1_Student_Realtime]")
        records = mssql_cursor.fetchall()

        # Get column names
        columns = [column[0] for column in mssql_cursor.description]

        # Connect to MySQL
        mysql_conn = connect_mysql()
        mysql_cursor = mysql_conn.cursor()

         # Insert or Update MySQL
        insert_update_query = f"""
            INSERT INTO D1_Student_Realtime ({', '.join(columns)}) 
            VALUES ({', '.join(['%s'] * len(columns))})
            ON DUPLICATE KEY UPDATE 
            {', '.join([f'{col} = VALUES({col})' for col in columns])}
        """

        for record in records:
            mysql_cursor.execute(insert_update_query, tuple(record))  # Convert Row to Tuple
            total_migrated += 1

        mysql_conn.commit()
        mysql_cursor.close()
        mysql_conn.close()
        mssql_cursor.close()
        mssql_conn.close()

        # Calculate time taken
        end_time = time.time()
        elapsed_time = round(end_time - start_time, 2)

        response = {
            "start_time": datetime.fromtimestamp(start_time).strftime("%Y-%m-%d %H:%M:%S"),
            "end_time": datetime.fromtimestamp(end_time).strftime("%Y-%m-%d %H:%M:%S"),
            "elapsed_time": elapsed_time,
            "total_migrated": total_migrated
        }

        print("Migration Response:", response)  # Debugging print
        return jsonify(response)

    except Exception as e:
        print("Error:", str(e))  # Debugging print
        return jsonify({"error": str(e)}), 500

# Start Flask Server
if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=5000)
