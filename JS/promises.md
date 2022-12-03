## Promises

```js
async function letPotKeepBoiling(time) {
  return new Promise((resolve) => setTimeout(() => {
    console.log("Pot has boiled for ", time, " minutes");
    resolve();
  }, time * 100));
}

async function boilPot() {
  return new Promise((resolve) => setTimeout(() => {
    console.log("Done boiling pot!");
    resolve();
  }, 5000));
}

async function makeSoup() {
  const pot = boilPot();
  chopCarrots();
  chopOnions();
  await pot;
  addCarrots();
  await letPotKeepBoiling(5);
  addOnions();
  await letPotKeepBoiling(10);
  console.log("Your vegetable soup is ready!");
}

makeSoup();
```

equals to 

```js
function makeSoup() {
  return Promise.all([
    new Promise((reject, resolve) => {
      chopCarrots();
      chopOnions();
      resolve();
    }),
    boilPot()
  ]).then(() => {
    addCarrots();
    return letPotKeepBoiling(5);
  }).then(() => {
    addOnions();
    return letPotKeepBoiling(10);
  }).then(() => {
    console.log("Vegetable soup done!");
  });
}
```