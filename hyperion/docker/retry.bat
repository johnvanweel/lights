docker -H 192.168.0.21 build -t lights .
docker -H 192.168.0.21 run --privileged -p 19445:19445 --device /dev/spidev0.0:/dev/spidev0.0 lights