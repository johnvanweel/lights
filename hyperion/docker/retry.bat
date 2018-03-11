docker -H 192.168.0.21 build -t lights .
docker -H 192.168.0.21 run --privileged --device /dev/spidev0.0:/dev/spidev0.0 lights