copy /Y ..\target\lightservice.jar .\lightservice.jar

docker -H 192.168.0.24 build -t lightservice .
docker -H 192.168.0.24 run --restart=always -p 8080:8080 lightservice