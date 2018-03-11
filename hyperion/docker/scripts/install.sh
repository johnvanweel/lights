#!/usr/bin/env bash

chmod +x ./install_hyperion.sh

sh ./install_hyperion.sh

#sleep 10

exec hyperiond /etc/hyperion/hyperion.config.json &

exec tail -f /dev/null