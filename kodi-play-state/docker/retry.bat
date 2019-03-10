copy /Y ..\target\kodi-play-service.jar .\kodi-play-service.jar

docker -H 192.168.0.23 build -t kodi-play-service .
docker -H 192.168.0.23 run --restart=always kodi-play-service