## Clone container

```
$ docker ps
CONTAINER ID   IMAGE COMMAND                  CREATED        STATUS                    PORTS          NAMES
d79c35c9103d   img   "/bin/sh -c 'exec $O…"   9 months ago   Up 37 minutes (healthy)   0.0.0.0:1521   hungry_sinoussi
```

```
$ docker commit -p d79c35c9103d hungry_sinoussi
$ docker images
REPOSITORY             TAG           IMAGE ID       CREATED          SIZE
hungry_sinoussi        latest        68bf517ba212   10 minutes ago   3.87GB
oracle/database-mpbx   11.2.0.2-xe   2fc5ca492f11   13 months ago    1.13GB
```

Save in file

```
# docker save -o ~/hungry_sinoussi.tar hungry_sinoussi
```

Import fromfile

```
# docker load -i /root/hungry_sinoussi.tar
# docker images
```

Send image to repository

```
$ docker login
$ docker push hungry_sinoussi
```

Original <https://itsecforu.ru/2021/05/27/%F0%9F%90%B3-%D0%BA%D0%BE%D0%BD%D1%82%D0%B5%D0%B9%D0%BD%D0%B5%D1%80-docker-%D1%80%D0%B5%D0%B7%D0%B5%D1%80%D0%B2%D0%BD%D0%BE%D0%B5-%D0%BA%D0%BE%D0%BF%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5/>

<https://gist.github.com/thaJeztah/8d0e901bd21329d80cf2>

Run cloned

```
$ docker run --name=hungry_sinoussi_clone hungry_sinoussi
```
