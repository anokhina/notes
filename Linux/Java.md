## Java alternatives

Command

```
sudo update-alternatives --config java
```

Prints

```
$ sudo update-alternatives --config java
[sudo] пароль для sevn: 
Есть 2 варианта для альтернативы java (предоставляет /usr/bin/java).

  Выбор   Путь                                        Приор Состояние
------------------------------------------------------------
  0            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      автоматический режим
  1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      ручной режим
* 2            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      ручной режим

Press <enter> to keep the current choice[*], or type selection number:
```

Add oracle java to alternatives

```
sudo update-alternatives --install /usr/bin/java java /usr/local/java/bin/java 2222
```

The highest priority for auto selection.

