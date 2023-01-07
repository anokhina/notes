
## Mongo spring repository name rules

| Keyword	Sample	| Logical | result | 
| --- | --- | --- |
| GreaterThan |	findByAgeGreaterThan(int age)	| {"age" : {"$gt" : age}}
| LessThan |	findByAgeLessThan(int age)	| {"age" : {"$lt" : age}}
| Between |	findByAgeBetween(int from, int to)	| {"age" : {"$gt" : from, "$lt" : to}}
| IsNotNull, NotNull |	findByFirstnameNotNull()	| {"age" : {"$ne" : null}}
| IsNull, Null |	findByFirstnameNull()	| {"age" : null}
| Like |	findByFirstnameLike(String name)	| {"age" : age} ( age as regex)
| Regex |	findByFirstnameRegex(String firstname)	| {"firstname" : {"$regex" : firstname }}
| (No keyword) |	findByFirstname(String name)	| {"age" : name}
| Not |	findByFirstnameNot(String name)	| {"age" : {"$ne" : name}}
| Near |	findByLocationNear(Point point)	| {"location" : {"$near" : [x,y]}}
| Within |	findByLocationWithin(Circle circle)	| {"location" : {"$within" : {"$center" : [ [x, y], distance]}}}
| Within |	findByLocationWithin(Box box)	| {"location" : {"$within" : {"$box" : [ [x1, y1], x2, y2]}}}True
| IsTrue, True |	findByActiveIsTrue()	| {"active" : true}
| IsFalse, False |	findByActiveIsFalse()	| {"active" : false}
| Exists |	findByLocationExists(boolean exists)	| {"location" : {"$exists" : exists }}