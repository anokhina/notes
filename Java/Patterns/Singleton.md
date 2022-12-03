## Singleton

```java
public class BillPughSingleton {

    private BillPughSingleton(){}
    
    private static class SingletonHelper{
    
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        
        public static BillPughSingleton getInstance(){
            return SingletonHelper.INSTANCE;
        }
    }
    
    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }    
}
```

## Enum singleton

```java
public enum EnumSingleton {
    INSTANCE;
    
    public static void doSomething(){
        //do something
    }
}
```

```java
import java.io.Serializable;

public class SerializedSingleton implements Serializable{
    private static final long serialVersionUID = -7604766932017737115L;
    
    private SerializedSingleton(){}
    
    private static class SingletonHelper{
        private static final SerializedSingleton instance = new SerializedSingleton();
    }
    
    public static SerializedSingleton getInstance(){
        return SingletonHelper.instance;
    }
    
    protected Object readResolve() {
        return getInstance();
    }    
}
```
