# jjdd8-materialy-rest


## Dokumentacja API - narzędzia

### Swagger

https://www.swagger.io

#### Uruchomienie edytora

Przygotowanie (ściągnięcie image):
```
docker pull swaggerapi/swagger-editor
```

Uruchomienie lokalnie:
```
docker run -p 8282:8080 swaggerapi/swagger-editor
```
Po uruchomieniu konsola dostępna pod adresem: http://localhost:8282

## Serwer

### Ważne polecenia

#### Uruchamianie bazy danych



```shell script
docker run --network isa-hibernate --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=isahibernate -d mysql
```

#### Uruchomienie klienta bazy danych

```shell script
docker exec -it mysql mysql -h mysql -uroot -p
```

#### Uruchomienie dockera z wildfly

Zbudowanie image dla wildFly:
```shell script
docker build --tag=isa-wildfly .
```

Później uruchamiać docker'a:
```shell script
docker run -it --rm -p 8080:8080 -p 9990:9990 --network isa-hibernate --name isa-wildfly isa-wildfly
```

