## Space usage

```
qdirstat
```

## List of discs

### SSD или жесткий диск (HDD) 

```
lsblk -o +ROTA,DISC-GRAN
```

**SSD**: DISC-GRAN != 0  
**HDD**: ROTA != 0 

### Показать файловые системы, хранящиеся на дисках / разделах

```
lsblk -o +FSTYPE,LABEL
```

### Показать съемные устройства / USB-накопители

```
lsblk -o +RM
```

### Показать модель HDD/SSD

```
lsblk -d -o +MODEL
```

### Показать UUID файловой системы (универсальный уникальный идентификатор)

```
lsblk -o +UUID
```

### Disc information

```
lsblk -o "NAME,MAJ:MIN,RM,SIZE,RO,FSTYPE,MOUNTPOINT,UUID"
smartctl -a /dev/yourdrive
```