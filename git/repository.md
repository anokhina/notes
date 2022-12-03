## Repository

Check size 

```
git bundle create tmp.bundle --all
du -sh tmp.bundle
```

or

```
git gc
du -sh .git/
```

```
git count-objects -vH
```

Clean old

```
git gc
git gc --aggressive
git prune
```

All deleted files

```
git log --diff-filter=D --summary
```

## Clean

```
git reflog expire --expire=now --all && git gc --prune=now --aggressive
```

```git reflog expire``` prunes all entries older than the current time and ```git gc``` removes unreachable files and recompresses the repository.

```git push```


List deleted files ```git log --diff-filter=D --summary | grep "delete mode"```

Delete all files  with bfg <https://rtyley.github.io/bfg-repo-cleaner/>

```
java -jar bfg.jar --delete-files "{somefilename1, somefilename2}.*" you_repo.git
```

## Push into new branch

Display current branch

```
git branch

branch1
branch2
*branch_current
branch3
```

Push local ```branch_current``` into new remote branch ```new_remote_branch```

```
git push origin branch_current:new_remote_branch
```

## Merge after moving files

```
git config merge.renameLimit 999999
```

and reset it to default

```
git config --unset merge.renameLimit
```

High limit value increases memory usage.

[Confluence, git, rename, merge oh my…](https://www.atlassian.com/blog/developer/2011/10/confluence_git_rename_merge_oh_my)
