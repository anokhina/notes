## Jython

<https://www.jython.org/index.html>

<https://jython.readthedocs.io/en/latest/>

[jython-readthedocs-io-en-latest.pdf](jython-readthedocs-io-en-latest.pdf)

```
<!-- https://mvnrepository.com/artifact/org.python/jython -->
<dependency>
    <groupId>org.python</groupId>
    <artifactId>jython</artifactId>
    <version>2.7.2</version>
</dependency>
```

```java
PythonInterpreter interpreter = new PythonInterpreter();
interpreter.exec("import sys\nsys.path.append('pathToModules if they are not there by default')\nimport yourModule");
// execute a function that takes a string and returns a string
PyObject someFunc = interpreter.get("funcName");
PyObject result = someFunc.__call__(new PyString("Test!"));
String realResult = (String) result.__tojava__(String.class);
```
