## Install

```
termux-setup-storage
```

### File manager

```
pkg in nnn
```
<https://github.com/jarun/nnn>

```hjkl``` -- arrows, ```/``` -- filter, ```q``` -- quit, ```?``` -- key shortcut list


### Editor

```
pkg install emacs
pkg upgrade
```

upgrade if some library is not found

### Git

```
pkg in git
pkg in openssh
```

## Settings

<https://wiki.termux.com/wiki/Terminal_Settings>

```
mkdir ~/.termux
nano ~/.termux/termux.properties
```

insert

```
extra-keys = [ \
 ['ESC','|','/','HOME','UP','END','PGUP','DEL'], \
 ['TAB','CTRL','ALT','LEFT','DOWN','RIGHT','PGDN','BKSP'] \
]
```

and save it with Ctrl+o

```
nano ~/.bashrc
```

add lines

```
git_branch() {
  git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/(\1)/'
}
export PS1="\W\[\033[00;32m\]\$(git_branch)\[\033[00m\]\$ "
```

and save it, and restart termux.

<http://tldp.org/HOWTO/Bash-Prompt-HOWTO/index.html>

## Short keys

<https://wiki.termux.com/wiki/Touch_Keyboard>

<https://wiki.termux.com/wiki/Hardware_Keyboard>


## python

```
pkg in openssl
pkg install python

pip install pkg -U
```


```
pkg install ffmpeg
```

### Repositories problems 403

```
pkg remove game-repo
pkg remove science-repo
pkg update
```
