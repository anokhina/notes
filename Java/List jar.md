## List jar file

```
try (FileSystem fs = FileSystems.newFileSystem(urlResource.getURI(), Collections.emptyMap())) {
    Files.walk(fs.getPath(pkg.replace(".", "/")))
            .filter(Files::isRegularFile)
            .forEach(e -> System.err.println(">?>" + e));
}  

```
