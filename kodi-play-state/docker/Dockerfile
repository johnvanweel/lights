FROM resin/raspberrypi3-buildpack-deps:jessie-scm

ENV LANG C.UTF-8
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN echo "deb http://archive.raspberrypi.org/debian/ jessie main ui staging" > /etc/apt/sources.list.d/raspi.list
RUN rm -f /usr/bin/entry.sh
RUN wget -qO - http://archive.raspberrypi.org/debian/raspberrypi.gpg.key | apt-key add -

RUN { \
        echo '#!/bin/bash'; \
        echo 'set -e'; \
        echo; \
        echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
    } > /usr/local/bin/docker-java-home && \
    chmod +x /usr/local/bin/docker-java-home

RUN apt-key adv --recv-key --keyserver keyserver.ubuntu.com C2518248EEA14886 && \
    echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" >> /etc/apt/sources.list.d/raspi.list

RUN set -x && \
    apt-get update && \
    apt-cache madison oracle-java8-installer && \
    echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    apt-get install -y oracle-java8-installer oracle-java8-set-default && \
    rm -rf /var/lib/apt/lists/* && \
    [ "$JAVA_HOME" = "$(docker-java-home)" ]

COPY kodi-play-service.jar /tmp/

ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /tmp/kodi-play-service.jar