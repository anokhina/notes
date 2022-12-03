Output integer in binary format.

```
System.out.println(String.format("%32s", Integer.toBinaryString(Integer.MAX_VALUE)).replace(' ', '0'));
System.out.println(String.format("%32s", Integer.toBinaryString(0b011)).replace(' ', '0'));
System.out.println(String.format("%32s", Integer.toBinaryString(0b11)).replace(' ', '0'));
```

prints

```
01111111111111111111111111111111
00000000000000000000000000000011
00000000000000000000000000000011
```