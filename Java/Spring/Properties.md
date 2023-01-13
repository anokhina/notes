## Properties

```
arrayOfStrings=Baeldung,dot,com
listOfStrings={'Baeldung','dot','com'}
listOfStringsWithCustomDelimiter=Baeldung;dot;com
listOfBooleans=false,false,true
listOfIntegers=1,2,3,4
listOfCharacters=a,b,c
```


```java
@Value("${arrayOfStrings}")
private String[] arrayOfStrings;

@Value("#{'${arrayOfStrings}'.split(',')}")
private List<String> listOfStrings;

@Value("#{${listOfStrings}}")
private List<String> listOfStringsV2;

@Value("#{'${listOfStringsWithCustomDelimiter}'.split(';')}")
private List<String> listOfStringsWithCustomDelimiter;

@Value("#{'${listOfBooleans}'.split(',')}")
private List<Boolean> listOfBooleans;

@Value("#{'${listOfIntegers}'.split(',')}")
private List<Integer> listOfIntegers;

@Value("#{'${listOfCharacters}'.split(',')}")
private List<Character> listOfCharacters;

@Autowired
private Environment environment;

void fromEnv() {
    String[] arrayOfStrings = environment.getProperty("arrayOfStrings", String[].class);
    List<String> listOfStrings = (List<String>)environment.getProperty("arrayOfStrings", List.class);
}
```