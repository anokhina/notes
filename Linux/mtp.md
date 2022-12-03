## Mount mtp

This way doesn't work in my kubuntu

```
sudo apt-get install jmtpfs

jmtpfs /tmp/myphone
```

jmtpfs will use the first available device. If you've got more than one connected at a time, you can do jmtpfs -l to find out which one is your phone, and use the -device flag to specify it.

## Mount with adb

<https://github.com/spion/adbfs-rootless>

```
sudo apt-get install libfuse-dev android-tools-adb
sudo apt-get install build-essential git pkg-config

git clone git://github.com/spion/adbfs-rootless.git
cd adbfs-rootless 

make

mkdir ~/droid

./adbfs ~/droid

fusermount -u /home/user/droid

```