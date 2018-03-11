#!/usr/bin/env bash

chmod +x ./install_hyperion.sh

sh ./install_hyperion.sh

exec hyperiond /etc/hyperion/hyperion.config.json &

# TODO: Remove
exec tail -f /dev/null