from flask import Flask, jsonify, send_from_directory
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

        # Insert into MySQL
        insert_query = f"""
            INSERT INTO D1_Student_Realtime ({', '.join(columns)}) 
            VALUES ({', '.join(['%s'] * len(columns))})
        """

        for record in records:
            mysql_cursor.execute(insert_query, tuple(record))  # Convert Row to Tuple
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
