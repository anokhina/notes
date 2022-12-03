## Oracle in docker

```
docker load -i oracle-database-mpbx.tar
docker images
docker run --shm-size=1g -p 1521:1521 -e ORACLE_PWD=orapss -p 8080:8080 oracle/database-mpbx:11.2.0.2-xe
```

<https://github.com/wnameless/docker-oracle-xe-11g>

<https://github.com/fuzziebrain/docker-oracle-xe>

## database is extremely slow

<https://github.com/oracle/docker-images/issues/601#issuecomment-509250667>

As a possible workaround one needs to check values for the following parameters in sqlplus:

```sql
SHOW PARAMETER FILESYSTEMIO_OPTIONS;
SHOW PARAMETER DISK_ASYNCH_IO;
```

Result:

```
NAME                 TYPE   VALUE 
-------------------- ------ ----- 
filesystemio_options string none  
NAME           TYPE    VALUE 
-------------- ------- ----- 
disk_asynch_io boolean TRUE  
```

Then disable async_io:

```sql
ALTER SYSTEM SET FILESYSTEMIO_OPTIONS=DIRECTIO SCOPE=SPFILE;
ALTER SYSTEM SET DISK_ASYNCH_IO=FALSE SCOPE=SPFILE;
```

and restart oracle (```/etc/init.d/oracle-xe restart```).

Result:

```
NAME                 TYPE   VALUE    
-------------------- ------ -------- 
filesystemio_options string DIRECTIO 
NAME           TYPE    VALUE 
-------------- ------- ----- 
disk_asynch_io boolean FALSE 
```

## Password expired

```
$ docker ps
CONTAINER ID   IMAGE                              COMMAND                  CREATED        STATUS                   PORTS                                            NAMES
abcd12345678   oracle/database-mpbx:11.2.0.2-xe   "/bin/sh -c 'exec $O…"   6 months ago   Up 6 minutes (healthy)   0.0.0.0:1521->1521/tcp, 0.0.0.0:8080->8080/tcp   names
$ docker exec -ti abcd12345678 bash
bash-4.2# su - oracle
-bash-4.2$ export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe             
-bash-4.2$ export ORACLE_SID=XE
-bash-4.2$ /u01/app/oracle/product/11.2.0/xe/bin/sqlplus / as sysdba

SQL*Plus: Release 11.2.0.2.0 Production on Tue Mar 9 14:49:19 2021

Copyright (c) 1982, 2011, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL> ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME
UNLIMITED PASSWORD_REUSE_TIME UNLIMITED PASSWORD_REUSE_MAX UNLIMITED;  2  

Profile altered.

SQL> ALTER USER user IDENTIFIED BY userpassword;

User altered.

SQL> Disconnected from Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
-bash-4.2$ logout
bash-4.2# exit
```