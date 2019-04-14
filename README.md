# Tinder Scarlet Lifecycle Android

This repository is an edited library from Scarlet's official repository while waiting for my [Pull request](https://github.com/Tinder/Scarlet/pull/71) to be approved.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![](https://jitpack.io/v/hantrungkien/tinder-scarlet-lifecycle-android.svg)](https://jitpack.io/#hantrungkien/tinder-scarlet-lifecycle-android)

<a><img src="https://raw.githubusercontent.com/Tinder/Scarlet/0.2.x/example/scarlet-state-machine.png" />
  
### Usage

```
val protocol = OkHttpWebSocket(
    okHttpClient,
    OkHttpWebSocket.SimpleRequestFactory(
        { Request.Builder().url("wss://ws-feed.gdax.com").build() },
        { ShutdownReason.GRACEFUL }
    )
)

/**
 * @param: application to detect connectivity change
 * @param: lifecycleOwner from Activity or Fragment to detect lifecycle
 */
val configuration = Scarlet.Configuration(
    lifecycle = AndroidLifecycle.ofLifecycleOwnerForeground(application, lifecycleOwner),
    messageAdapterFactories = listOf(MoshiMessageAdapter.Factory(moshi)),
    streamAdapterFactories = listOf(RxJava2StreamAdapterFactory())
)
val scarletInstance = Scarlet(protocol, configuration)
val gdaxService = scarletInstance.create<GdaxService>()
```

### Install:

**via JitPack (to get current code)**

project/build.gradle
````gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
````
module/build.gradle
````gradle
implementation 'com.github.hantrungkien:tinder-scarlet-lifecycle-android:1.0.0'
````

### LICENCE

    Copyright 2018 Kien Han Trung

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
