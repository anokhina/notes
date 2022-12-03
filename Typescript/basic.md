## Compile to js when there is no error in ts

```
tsc --noEmitOnError hello.ts
```

## Convert to specified js version

```
tsc --target es2015 hello.ts
```

## Run in browser

<https://www.typescriptlang.org/play>

```html
<html>
<script src="https://unpkg.com/typescript@latest/lib/typescriptServices.js"></script>

<body>
<div id="main" />
<script>
const tsCode = `
let simpleText: string = "TypeScript can be transpiled in browser";
function testTS(message: string) {
  const main = document.getElementById('main');
  main.innerText = message;
}
testTS(simpleText);
`;
const jsCode = window.ts.transpile(tsCode);
eval(jsCode);
</script>
</body>
</html>
```