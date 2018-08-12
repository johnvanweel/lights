FROM resin/rpi-raspbian

RUN apt-get update && apt-get install -y dos2unix libqtcore4 libqtgui4 libqt4-network libusb-1.0-0 ca-certificates libpython2.7 wget libraspberrypi0 libraspberrypi-dev libraspberrypi-doc libraspberrypi-bin

COPY scripts/*.sh /tmp/
COPY config/hyperion.config.json /etc/hyperion/hyperion.config.json

RUN dos2unix /tmp/*.sh

WORKDIR "/tmp"

#TODO: No runtime installer
CMD ["sh", "/tmp/install.sh"]