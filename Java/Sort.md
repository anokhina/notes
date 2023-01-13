## Sort cillection

```
.stream()
  .sorted(Comparator.comparing(Foo::getSize)) //comparator - how you want to sort it
  .collect(Collectors.toList()); //collector - what you want to collect it to
```
