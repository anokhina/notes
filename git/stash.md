To list the stashed modifications

```
git stash list
```

To show files changed in the last stash

```
git stash show
```

So, to view the content of the most recent stash, run

```
git stash show -p
```

To view the content of an arbitrary stash, run something like

```
git stash show -p stash@{1}
```

to check a single file out of the stash:

```
$ git checkout stash@{0} -- <filename>
```

or to save it under another filename:

```
$ git show stash@{0}:<full filename>  >  <newfile>
```

or

```
$ git show stash@{0}:./<relative filename> > <newfile>
```
