import os

API_KEY = os.environ.get('API_KEY', 'default-api-key')
MSSQL_CONFIG = {
    "server": os.environ.get('MSSQL_SERVER', '113.23.136.34'),
    "database": os.environ.get('MSSQL_DATABASE', 'TESTINGDB'),
    "username": os.environ.get('MSSQL_USERNAME', 'user_viewtestingdb'),
    "password": os.environ.get('MSSQL_PASSWORD', 'user_viewtestingdb#131024$^~')
}
MYSQL_CONFIG = {
    "host": os.environ.get('MYSQL_HOST', 'localhost'),
    "user": os.environ.get('MYSQL_USER', 'root'),
    "password": os.environ.get('MYSQL_PASSWORD', ''),
    "database": os.environ.get('MYSQL_DATABASE', 'kptrtdb')
}
