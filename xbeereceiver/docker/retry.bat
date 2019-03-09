copy /Y ..\target\xbee-receiver-1.0-SNAPSHOT.jar .\xbee.jar

docker -H 192.168.0.23 build -t xbee .
docker -H 192.168.0.23 run --restart=always --privileged --device /dev/ttyUSB0:/dev/ttyUSB0 xbee