#!/usr/bin/env bash
#rm /var/lock/* || true

ls -lrt .

java -jar -Djava.library.path=/usr/lib/jni /tmp/xbee.jar
