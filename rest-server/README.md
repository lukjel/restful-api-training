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

**W katalogu** `wildfly.docker` uruchomić najpierw:
```shell script
./build.sh
```

Później uruchamiać docker'a:
```shell script
./run.sh
```

