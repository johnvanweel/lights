FROM resin/raspberry-pi-openjdk

RUN apt-get update && apt-get install -y librxtx-java

WORKDIR "/tmp"

COPY xbee.jar /tmp/

ENTRYPOINT ["java","-jar", "-Djava.library.path=/usr/lib/jni", "/tmp/xbee.jar"]