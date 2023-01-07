## Empty arrays: unwind

```json
{
  "_id": "<id>",
  "login": "xxx",
  "solved": [
    {
      "problem": "<problemID>",
      "points": 10
    },
    ...
  ]
}
```

solved - may be empty


```JavaScript
db.collection.aggregate([
    { "$unwind": {
        "path": "$solved",
        "preserveNullAndEmptyArrays": true
    } },
    { "$group": {
        "_id": "$_id",
        "login": { "$first": "$login" },
        "solved": { "$sum": "$solved.points" }
    } }
])
```