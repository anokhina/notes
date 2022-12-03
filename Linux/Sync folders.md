## Sunc folders

```
rsync -avu folder1/ folder2/
```

<https://download.samba.org/pub/rsync/rsync.html>

## Backup folder content

From <https://www.jveweb.net/en/archives/2011/02/using-rsync-and-cron-to-automate-incremental-backups.html>


```
rsync -ab --backup-dir=_old_`date +%F-%H%M%S` --delete --exclude=_old_* test1 test2
```

Moves old files in folder ```_old_YYYY-MM-DD-HHMMSS```

To create folder with different files

```
cd test1
export difftime=`date +%F-%H%M%S`
rsync -acv --compare-dest=../test2/test1 . ../diff$difftime
cd ../diff$difftime
find . -d -type d -empty -exec rmdir {} \; -print
```


```
export difftime=`date +%F`
cd /media/sevn/hp17files/UCHEBNIKI
rsync -acv --compare-dest=/media/sevn/PLEXTOR/BUP/UCHEBNIKI . /media/sevn/PLEXTOR/DIFF/UCHEBNIKI$difftime
cd /media/sevn/PLEXTOR/DIFF/UCHEBNIKI$difftime
find . -d -type d -empty -exec rmdir {} \; -print
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/UCHEBNIKI  /media/sevn/PLEXTOR/BUP
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/UCHEBNIKI-video  /media/sevn/PLEXTOR/BUP
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/PROGRAMMING  /media/sevn/PLEXTOR/BUP
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/FICTION  /media/sevn/PLEXTOR/BUP
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/LIB  /media/sevn/PLEXTOR/BUP
rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* /media/sevn/hp17files/LIBA  /media/sevn/PLEXTOR/BUP

rsync -ab --backup-dir=_old_$difftime --delete --exclude=_old_* --exclude=target/ /home/sevn/winh/work/dmpbx /media/sevn/hp17files/WORK/REPO
```

## ext to vfat

```
ext3 filesystem to a vfat/fat16/fat32 one (e.g. a USB stick):

rsync -a --no-o --no-p --no-g --safe-links --modify-window 1 --stats /home/source/ /media/sda1/dest/

--no-o because vfat does not support owners
--no-g because vfat does not support groups
--no-p because vfat does not support permissions
--modify-window 1 because fat "represents times with a 2-second resolution" (rsync manual)
--safe-links because vfat does not support symbolic links

Also, note that vfat does not support filenames containing '%' or ':'.
To check where they are and whether they are not important: $ find . -name "*%*" -or -name "*:*
```

## notes

```
export difftime=`date +%F`

cd /home/files/UCHEBNIKI
rsync -acv --compare-dest=/media/sevn/store2t/home/files/files/UCHEBNIKI . /media/sevn/store2t/home/DIFF/UCHEBNIKI$difftime
cd /media/sevn/store2t/home/DIFF/UCHEBNIKI$difftime
find . -d -type d -empty -exec rmdir {} \; -print

cd /home/files/PROGRAMMING
rsync -acv --compare-dest=/media/sevn/store2t/home/files/files/PROGRAMMING . /media/sevn/store2t/home/DIFF/PROGRAMMING$difftime
cd /media/sevn/store2t/home/DIFF/PROGRAMMING$difftime
find . -d -type d -empty -exec rmdir {} \; -print


```
