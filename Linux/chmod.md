## Recursive Chmod For dirs

```
find . -type d -exec chmod 0755 {} \;
```

## Recursive Chmod For files

```
find . -type f -exec chmod 0644 {} \;
```