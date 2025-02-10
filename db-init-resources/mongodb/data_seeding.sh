#!/bin/bash

# Import restaurant menus into MongoDB
echo "Starting MongoDB import for restaurant menus..."
mongoimport --host localhost --port 27017 --db menus --collection restaurants_menus --file /docker-entrypoint-initdb.d/restaurant_menus.json --jsonArray

if [ $? -eq 0 ]; then
  echo "MongoDB import completed successfully."
else
  echo "Error during MongoDB import. Please check the logs." >&2
  exit 1
fi
