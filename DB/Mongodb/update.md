## Update object parameter

```java
Query query = new Query();
query.addCriteria(Criteria.where("name").is("appleC"));
query.fields().include("name");

User userTest3 = mongoOperation.findOne(query, User.class);

Update update = new Update();
update.set("age", 100);

mongoOperation.updateFirst(query, update, User.class);
//mongoOperation.updateMulti(query, update, User.class);

// or create new one if not found
//mongoOperation.upsert(query, update, User.class);

// Find and modify
//User userTest6 = mongoOperation.findAndModify(query6, update6,
//	new FindAndModifyOptions().returnNew(true), User.class);
```