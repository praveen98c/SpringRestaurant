import pandas as pd
import psycopg2
from decimal import Decimal

# Database connection details
DB_HOST = "127.0.0.1"
DB_PORT = "5432"
DB_NAME = "db_spring_restaurant"
DB_USER = "admin"
DB_PASSWORD = "1234"

# Connect to PostgreSQL
try:
    conn = psycopg2.connect(
        host=DB_HOST, port=DB_PORT, database=DB_NAME, user=DB_USER, password=DB_PASSWORD
    )
    cursor = conn.cursor()
    print("Connected to PostgreSQL successfully!")
except Exception as e:
    print("Error connecting to PostgreSQL:", e)
    exit()

# Read the Excel file
file_path = "restaurants.xlsx"
xls = pd.ExcelFile(file_path)

cursor.execute("SET search_path TO sc_spring_restaurant;")

### 1. Insert Restaurants
df_restaurants = pd.read_excel(xls, sheet_name="Restaurants")

for _, row in df_restaurants.iterrows():
    cursor.execute(
        "INSERT INTO restaurant (id, name, location, phone, email, image_url, created_at) VALUES (%s, %s, %s, %s, %s, %s, %s) RETURNING id;",
        (int(row["restaurant_id"]), row["name"], row["location"], row["phone"], row["email"], row["image_url"], pd.to_datetime(row["created_at"])),
    )
    restaurant_id = cursor.fetchone()[0]
    print(f"Inserted Restaurant: {row['name']} (ID: {restaurant_id})")

### 2. Insert Menus
df_menus = pd.read_excel(xls, sheet_name="Menus")

for _, row in df_menus.iterrows():
    cursor.execute(
        "INSERT INTO menu (id, restaurant_id, name, description, image_url, created_at) VALUES (%s, %s, %s, %s, %s, %s) RETURNING id;",
        (int(row["menu_id"]), int(row["restaurant_id"]), row["name"], row["description"], row["image_url"], pd.to_datetime(row["created_at"])),
    )
    menu_id = cursor.fetchone()[0]
    print(f"Inserted Menu: {row['name']} (ID: {menu_id})")

### 3. Insert Food Items
df_food_items = pd.read_excel(xls, sheet_name="FoodItems")

for _, row in df_food_items.iterrows():
    cursor.execute(
        "INSERT INTO food_item (id, menu_id, name, cuisine, description, rating, price, image_url, created_at) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s) RETURNING id;",
        (int(row["food_item_id"]), int(row["menu_id"]), row["name"], row["cuisine"], row["description"], float(row["rating"]), Decimal(row["price"]), row["image_url"], pd.to_datetime(row["created_at"])),
    )

    food_id = cursor.fetchone()[0]
    print(f"Inserted Food Item: {row['name']} (ID: {food_id})")

# Commit changes and close connection
conn.commit()
cursor.close()
conn.close()
print("Excel data imported successfully!")
