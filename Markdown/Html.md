## From HTML

<https://pandoc.org/getting-started.html>

```
pandoc --from html --to markdown doc.html -o doc.md

pandoc --from html --to markdown --extract-media=media doc.html -o doc.md
```