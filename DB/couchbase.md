## couchbase

No sql database 

### Installation

<https://docs.couchbase.com/server/current/install/rhel-suse-install-intro.html>

### Tutorials

<https://docs.couchbase.com/tutorials/index.html>

<https://www.baeldung.com/n1ql-couchbase>

### Docs

<https://docs.couchbase.com/couchbase-lite/2.7/java-platform.html>

### Example

```
        <dependency>
            <groupId>com.couchbase.lite</groupId>
            <artifactId>couchbase-lite-java</artifactId>
            <version>2.8.1</version>
        </dependency>
        
```

```
private static final String DB_PATH = "/tmp/dbfolder";
private static final String DB_NAME = "dbsample";

    private void init () {
        CouchbaseLite.init ();
        final DatabaseConfiguration config = new DatabaseConfiguration ();
        config.setDirectory (DB_PATH);
        try {
            //config.setEncryptionKey(new EncryptionKey(DB_PASS)); //only for enterprize
            database = new Database (DB_NAME, config);
        }
        catch (CouchbaseLiteException ex) {
            throw new RuntimeException (ex);
        }
    }

        final MutableDocument md;
        database.save (md);

    public ResultSet query (final int limit, final int offset, final String [] properties, final String... params) throws CouchbaseLiteException {
        Expression expr = Expression.booleanValue (true);
        for (int i = 1; i < params.length; i += 2) {
            final Expression e = Expression.property (params [i - 1]).equalTo (Expression.string (params [i]));
            expr.and (e);
        }

        final SelectResult [] srs = new SelectResult [properties.length];
        for (int i = 0; i < properties.length; i++) {
            srs [i] = SelectResult.expression (Expression.property (properties [i]));
        }
        Query query = QueryBuilder.select (srs)
                .from (DataSource.database (database))
                .where (expr)
                .limit (Expression.intValue (limit), Expression.intValue (offset));
        final ResultSet result = query.execute ();
        //for (final Result r : result.allResults()) {
        //r.getKeys()
        //r.getString(k)
        //}
        return result;
    }

```