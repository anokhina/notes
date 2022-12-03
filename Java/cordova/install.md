## Install Cordova

Install Android Studio at first.

```
sudo apt-get install gradle
```

```
sudo npm install -g cordova

cordova create project_name
cordova create svphone ru.org.sevn.cordova.svphone SVPhone

cordova platform add android
cordova platform add browser
cordova platform add ios

export ANDROID_SDK=$HOME/Android/Sdk
export PATH=$ANDROID_SDK/emulator:$ANDROID_SDK/tools:$PATH

cordova plugin add cordova-plugin-camera

~/Android/Sdk/emulator/emulator -list-avds
~/Android/Sdk/emulator/emulator -avd Pixel_3a_API_30_x86

```