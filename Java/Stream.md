## Join array

```java
import java.util.Arrays;

import java.util.stream.IntStream;
import java.util.stream.Stream;

String[] result = Stream.of(array1, array2, array3).flatMap(Stream::of).toArray(String[]::new);

int[] result2 = IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).toArray();

int[] result3 = IntStream.concat(Arrays.stream(array1), IntStream.concat(Arrays.stream(array2), Arrays.stream(array3))).toArray();

```