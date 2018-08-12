copy /Y ..\target\light-service-1.0-SNAPSHOT.jar .\lights.jar

docker build -t lights .
docker tag lights 192.168.0.19:32999/johnvanweel/lights:1
docker push 192.168.0.19:32999/johnvanweel/lights:1

