```
git clean -d -x -f
```

will remove untracked files, including directories ( -d
) and files ignored by git ( -x
). Replace the -f
argument with -n
to perform a dry-run or -i
for interactive mode, and it will tell you what will be removed
