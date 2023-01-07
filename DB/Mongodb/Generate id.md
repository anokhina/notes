## Generate id


```
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public abstract class BasicFixRecordIdMongoEventListener<E extends EntityBase> extends AbstractMongoEventListener<E> {

    private final RecordIdFixer<E> recordIdFixer;

    protected BasicFixRecordIdMongoEventListener (final RecordIdFixer<E> recordIdFixer) {
        this.recordIdFixer = recordIdFixer;
    }

    @Override
    public void onBeforeConvert (final BeforeConvertEvent<E> event) {
        super.onBeforeConvert (event);
        recordIdFixer.fixRecordId (event.getSource ());
    }
}

@Component
public class EntityBaseMongoEventListener extends BasicLogTimeMongoEventListener<DbEntity> {

}

public class RecordIdFixer<E extends EntityBase> {
    private final SequenceGenerator sequenceGenerator;
    private final String sequenceName;

    public RecordIdFixer (final SequenceGenerator sequenceGenerator, final String sequenceName) {
        this.sequenceGenerator = sequenceGenerator;
        this.sequenceName = sequenceName;
    }

    public void fixRecordId (final E entity) {
        sequenceGenerator.fixRecordId (entity, sequenceName);
    }
}


@Service
public class SequenceGeneratorService extends SequenceGenerator {

    @Autowired
    public SequenceGeneratorService (MongoOperations mongoOperations) {
        super (mongoOperations);
    }
}


import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class SequenceGenerator {

    private final MongoOperations mongoOperations;

    public SequenceGenerator (final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence (final String seqName) {
        return generateSequence (mongoOperations, seqName);
    }

    public static Long setSequence (final MongoOperations mongoOperations, final String seqName, final long startValue) {
        DatabaseSequence counter = mongoOperations.findAndModify (query (where ("_id").is (seqName)),
                new Update ().set ("seq", startValue), options ().returnNew (true).upsert (true),
                DatabaseSequence.class);
        return ! Objects.isNull (counter) ? counter.getSeq () : null;
    }

    public static long generateSequence (final MongoOperations mongoOperations, final String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify (query (where ("_id").is (seqName)),
                new Update ().inc ("seq", 1), options ().returnNew (true).upsert (true),
                DatabaseSequence.class);

        return ! Objects.isNull (counter) ? counter.getSeq () : 1;
    }

    public <E extends EntityBase> E fixRecordId (final E entity, final String sequenceName) {
        if (entity.getRecordId () == null || entity.getRecordId () == 0) {
            entity.setRecordId (generateSequence (sequenceName));
        }

        return entity;
    }
}


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString (onlyExplicitlyIncluded = true)
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Jacksonized
@SuperBuilder
@Document
public class DatabaseSequence {

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;

    @ToString.Include
    @EqualsAndHashCode.Include
    private long seq;

}

```
