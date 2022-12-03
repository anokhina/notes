## grub settings

```
sudo nano /etc/default/grub
```

Edit line

```
GRUB_CMDLINE_LINUX_DEFAULT="fsck.repair=yes"
```

Update grub settings

```
sudo update-grub
```

## grub command line

<https://www.linux.com/training-tutorials/how-rescue-non-booting-grub-2-linux/>

for paging long command outputs:

```
grub> set pager=1
```

```
grub> ls
(hd0) (hd0,msdos2) (hd0,msdos1)

grub> ls (hd0,1)/
lost+found/ bin/ boot/ cdrom/ dev/ etc/ home/  lib/
lib64/ media/ mnt/ opt/ proc/ root/ run/ sbin/ 
srv/ sys/ tmp/ usr/ var/ vmlinuz vmlinuz.old 
initrd.img initrd.img.old

grub> cat (hd0,1)/etc/issue
Ubuntu 14.04 LTS n l

grub> set root=(hd0,1)
grub> linux /boot/vmlinuz-3.13.0-29-generic root=/dev/sda1
grub> initrd /boot/initrd.img-3.13.0-29-generic
grub> boot
```

hd0,1 = /dev/sda1. hd1,1 = /dev/sdb1. hd3,2 = /dev/sdd2.

For symbolic links

```
vmlinuz -> boot/vmlinuz-3.13.0-29-generic
initrd.img -> boot/initrd.img-3.13.0-29-generic
```

may use

```
grub> set root=(hd0,1)
grub> linux /vmlinuz root=/dev/sda1
grub> initrd /initrd.img
grub> boot
```

## repair after boot

```
# update-grub
# grub-install /dev/sda
```

## grub rescue

load the normal.mod andlinux.mod modules at first.

```
grub rescue> set prefix=(hd0,1)/boot/grub
grub rescue> set root=(hd0,1)
grub rescue> insmod normal
grub rescue> normal
grub rescue> insmod linux
grub rescue> linux /boot/vmlinuz-3.13.0-29-generic root=/dev/sda1
grub rescue> initrd /boot/initrd.img-3.13.0-29-generic
grub rescue> boot
```
