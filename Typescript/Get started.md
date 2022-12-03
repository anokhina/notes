# Get started

```
npm install -g typescript
mkdir myapp
cd myapp
tsc --init
```

Instal application bundler <https://parceljs.org/getting_started.html>

```
sudo npm install -g parcel-bundler
```

Edit [tsconfig.json](tsconfig.json)


Create index.html and index.ts

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
  </head>
  <body></body>
  <script src="./index.ts"></script>
</html>
```

```ts
console.log("Hello All from TypeScript!")
```

Run

```
parcel index.html
```

It starts server on http://localhost:1234 

Edit package.json 

```json
{
  "devDependencies": {
    "typescript": "^4.3.5"
  },
  "scripts": {
    "dev": "parcel index.html",
    "build": "parcel build index.html"
  }
}
```

Start app

```
npm run dev
```

Add jquery

```
npm install @types/jquery -D
npm install @types/jqueryui -D

npm install --save @types/jqueryui
npm install @types/jquery --save-dev
```

```
npm install @types/jquery --save-dev
npm install jquery --save

npm install @types/jqueryui --save-dev
npm install jqueryui --save
```

---

<https://dev.to/glebirovich/setting-up-typescript-project-with-webpack-4ode>
<https://appdividend.com/2017/03/31/beginners-guide-to-setup-typescript-with-webpack/>
<https://proglib.io/p/webpack-in-15/>

---

```
tsc --init
npm init
```

Create index.html and app.ts


```
npm install --save webpack-jquery-ui

npm install --save @types/jqueryui
npm install --save @types/jquery
npm install --save-dev ts-loader
npm install --save-dev webpack-dev-server
npm install --save-dev webpack-cli

npm install --save-dev webpack-dev-server

webpack server --port 7777

```
