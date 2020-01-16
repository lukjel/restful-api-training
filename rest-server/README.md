# jjdd8-materialy-rest

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

