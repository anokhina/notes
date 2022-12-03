## Get entity manager db url

```java
System.out.println(">>*>" + entityManager.unwrap(java.sql.Connection.class).getMetaData().getURL());
```
