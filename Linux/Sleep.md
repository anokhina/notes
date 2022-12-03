**s2disk** - the reference implementation of the userspace software suspend (µswsusp); it coordinates the steps necessary to suspend the system (such as freezing the processes, preparing the swap space, etc.) and handles image writing and reading. s2disk already supports compression and encryption of the image and other features (e.g. a nice progress bar, saving the image on a remote disk, playing tetris while resuming, etc.) can be easily added.

<https://wiki.archlinux.org/index.php/Uswsusp>

```
$ sudo apt install pm-utils hibernate
$ cat /sys/power/state
freeze mem disk
$ grep swap /etc/fstab
```

```
$ sudo nano /etc/default/grub
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash resume=UUID=YOUR_VALUE"
$ sudo update-grub
```

```
$ sudo systemctl hibernate
```