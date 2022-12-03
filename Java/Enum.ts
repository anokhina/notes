## Reflection for enum

```java
      for (E enumConstant : enumClass.getEnumConstants()) {
          try {
              Field declaredField = enumClass.getDeclaredField(enumConstant.name());
	          if (declaredField != null) {//never
	              A annotation = declaredField.getAnnotation(annotationType);
	              if (annotation != null) {
	                  //...
	              }
	          }
          } catch (NoSuchFieldException e) {
              //never 
              e.printStackTrace();
          }
      }
```