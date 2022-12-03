## Other user run

```
echo $DISPLAY
xhost +SI:localuser:user
```

where ```user``` is username.

```
$ su - user
$ export DISPLAY=:0
$ xcalc
```

