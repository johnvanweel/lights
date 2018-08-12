copy /Y ..\target\light-service-1.0-SNAPSHOT.jar .\lights.jar

docker -H 192.168.0.21 build -t lights .
docker -H 192.168.0.21 run -p 8080:8080 lights