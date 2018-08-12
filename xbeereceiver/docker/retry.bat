copy /Y ..\target\xbee-receiver-1.0-SNAPSHOT-jar-with-dependencies.jar .\xbee.jar

docker -H 192.168.0.21 build -t xbee .
docker -H 192.168.0.21 run --privileged --device /dev/ttyUSB0:/dev/ttyUSB0 xbee