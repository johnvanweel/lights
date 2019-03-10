**Pi preparation**

*Expose Docker to dev machine*

- sudo nano /lib/systemd/system/docker.service
- Change this line: ExecStart=/usr/bin/dockerd -H fd:// -H tcp://0.0.0.0:2375
- sudo systemctl daemon-reload
- sudo systemctl restart docker


*Enable SPI*

- Login to RPI
- Add dtparam=spi=on to /boot/config.txt


*TODO*
