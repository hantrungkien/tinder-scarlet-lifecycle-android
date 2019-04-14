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

### Copyright

~~~
Copyright (c) 2018, Match Group, LLC
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of Match Group, LLC nor the names of its contributors
      may be used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL MATCH GROUP, LLC BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
~~~
