  echo "Remove old db"
  docker container rm -f db 2>&1
  echo "Pull postgres image"
  docker pull postgres:12.3-alpine
  echo "Run postgres..."
  docker container run -d --name db --publish 5432:5432 -e POSTGRES_PASSWORD=postgres postgres:12.3-alpine

  echo "Create db"
  sleep 5
  docker exec db bash -c "
psql -U postgres -c '
CREATE DATABASE restaurantdemoappdb
    WITH
    OWNER = postgres
    ENCODING = "UTF8"
    CONNECTION LIMIT = -1;
    '"