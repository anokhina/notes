---
output:
  html_document:
    css: style.css
---

## Jackson

<https://github.com/eugenp/tutorials/tree/master/jackson-modules/jackson-custom-conversions>

## Custom serialisation

**Property filter sample**

```Java
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public class JacksonStartWithPropertyFilter extends JacksonAbstractPropertyFilter {

    public static final String EXCLUDE_DINAMIC_ENTITY_PROPERTIES_MIXIN = "EXCLUDE_DINAMIC_ENTITY_PROPERTIES";

    @JsonFilter (EXCLUDE_DINAMIC_ENTITY_PROPERTIES_MIXIN)
    public static class Mixin {
        public static final String EXCLUDE_DINAMIC_ENTITY_PROPERTIES = EXCLUDE_DINAMIC_ENTITY_PROPERTIES_MIXIN;
    }

    private final String [] startWith;

    public JacksonStartWithPropertyFilter (final String... startWith) {
        this.startWith = startWith;
    }

    public boolean isExcluded (final PropertyWriter writer) {
        for (final String sw : startWith) {
            if (writer.getName ().startsWith (sw)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean include (final BeanPropertyWriter writer) {
        return ! isExcluded (writer);
    }

    @Override
    protected boolean include (final PropertyWriter writer) {
        return ! isExcluded (writer);
    }

}
```

**Collect several filters in one**

```java
public class JacksonPropertyFilter extends SimpleBeanPropertyFilter {

    public static final String MULTI_EXCLUDE_ENTITY_PROPERTIES_MIXIN = "MULTI_EXCLUDE_ENTITY_PROPERTIES";

    @JsonFilter (MULTI_EXCLUDE_ENTITY_PROPERTIES_MIXIN)
    public static class Mixin {
        public static final String MULTI_EXCLUDE_ENTITY_PROPERTIES = MULTI_EXCLUDE_ENTITY_PROPERTIES_MIXIN;
    }

    private final JacksonAbstractPropertyFilter [] filters;

    public JacksonPropertyFilter (final JacksonAbstractPropertyFilter... filters) {
        this.filters = filters;
    }

    @Override
    protected boolean include (final BeanPropertyWriter writer) {
        for (final JacksonAbstractPropertyFilter f : filters) {
            if (! f.isIncluded (writer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean include (final PropertyWriter writer) {
        for (final JacksonAbstractPropertyFilter f : filters) {
            if (! f.isIncluded (writer)) {
                return false;
            }
        }
        return true;
    }

}
```

**Custom serializer**

```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class JacksonAddClassSerializer<T> extends StdSerializer<T> {

    private final PropertySerializer propertySerializer;

    public JacksonAddClassSerializer (final Class<T> clazz) {
        this (clazz, null);
    }

    public JacksonAddClassSerializer (final Class<T> clazz, final PropertySerializer ps) {
        super (clazz);
        this.propertySerializer = ps;
    }

    @Override
    public void serialize (final T value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        jgen.writeStartObject ();
        writeFields (value, jgen, provider);
        writeExtraFields (value, jgen, provider);
        jgen.writeEndObject ();
    }

    protected void writeExtraFields (final T value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        jgen.writeObjectField ("clazz", getClassName (value.getClass ()));
    }

    protected String getClassName (final Class clazz) {
        if (clazz.isArray ()) {
            return clazz.getSimpleName ();
        }
        return clazz.getName ();
    }

    protected void writeFields (final T value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        final JavaType javaType = provider.constructType (value.getClass ());
        final BeanDescription beanDesc = provider.getConfig ().introspect (javaType);
        final JsonSerializer<Object> serializer = BeanSerializerFactory.instance.findBeanSerializer (provider, javaType, beanDesc);
        if (value != null && propertySerializer != null) {
            for (final BeanPropertyDefinition p : beanDesc.findProperties ()) {
                if (propertySerializer.isSpecialField (value.getClass (), p.getRawPrimaryType ())) {
                    try {
                        propertySerializer.serializeField (p, value, jgen, provider);
                    }
                    catch (Exception e) {
                        throw new RuntimeException (e);
                    }
                }
            }
        }
        serializer.unwrappingSerializer (null).serialize (value, jgen, provider);
    }

    public interface PropertySerializer {
        boolean isSpecialField (final Class beanClass, final Class fieldClass);

        void serializeField (final BeanPropertyDefinition prop, final Object bean, final JsonGenerator gen, final SerializerProvider provider) throws Exception;
    }

}
```

**Build serializer**

```java

            final JacksonFieldSerializer res = new JacksonFieldSerializer ();
            res.addFilter (JacksonPropertyFilter.MULTI_EXCLUDE_ENTITY_PROPERTIES_MIXIN,
                    new JacksonPropertyFilter (
                            new JacksonStartWithPropertyFilter ("_prefix_"),
                            new JacksonEqualsPropertyFilter ("someField")))
                    .addMixIn (SomeClass.class, JacksonPropertyFilter.Mixin.class)
                    .addSerializer (SomeClass.class, new JacksonObjectSerializer (SomeClass.class, new MyPropertySerializer ()))
                    .setPretty (true);
```

**Replace property with something**

```java
    static class MyPropertySerializer implements JacksonAddClassSerializer.PropertySerializer {

        @Override
        public boolean isSpecialField (Class beanClass, Class fieldClass) {
            return (SomeSpecialClass.class.isAssignableFrom (beanClass) && SomeSpecialClass.class.isAssignableFrom (fieldClass));
        }

        @Override
        public void serializeField (BeanPropertyDefinition p, Object value, JsonGenerator jgen, SerializerProvider provider) throws Exception {
            if (value != null) {
                final SomeSpecialClass v = (SomeSpecialClass) BeanUtil.getFieldValue (value, p.getName ());
                if (v != null) {
                    jgen.writeObjectField (p.getName (), new ObjectRef (v));
                }
            }
        }

    }

```