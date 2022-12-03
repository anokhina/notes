## exFat format

Find partition
```
sudo fdisk -l
```

Format
```
sudo mkfs.exfat -n LABEL /dev/sdXn
```

Check it
```
sudo fsck.exfat /dev/sdXn
```