## Termux

Install from f-droid.

```
pkg install python
pkg install ffmpeg
pkg install openssl
pip install --upgrade pip
pip install youtube-dl
youtube-dl --extract-audio --audio-format mp3 url
pkg install git
pkg install nano


```

### Access to phone storage

```
termux-setup-storage

```

### Termux commands

```
pkg install termux-api
```

### Termux update

```
pkg update
pkg upgrade
```

### Termux keyboard

<https://wiki.termux.com/wiki/Touch_Keyboard>


### ssh

```
apt install openssh
```

Generate a key pair

```
cd ~./ssh
ssh-keygen -b 4096 -t rsa -C "key name comment"
```
It asks file name to generate two files filename and filename.pub and passphrase(leave it empty).

Add key. Start ssh agent.

```
eval "$(ssh-agent -s)"
```

Add key 

```
ssh-add ~/.ssh/filename
cat filename.pub
```

copy content of filename.pub and add it to github keys.


```
git clone git@github.com:user/reponame.git
git clone git@bitbucket.org:user/reponame.git

git config --global --add safe.directory your-git-dirname
git config --local user.name "User Name"
git config --local user.email "user@gmail.com"
git status
git add -A
git commit -m "initial commit"
```

### Install java

```
apt update
apt search openjdk
```


```
apt install openjdk-17
```