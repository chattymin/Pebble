# Pebble [![GitHub License](https://img.shields.io/badge/License-Apache%202.0-brightgreen.svg)](https://opensource.org/licenses/Apache-2.0) [![MIN SDK](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)]([https://opensource.org/licenses/Apache-2.0](https://android-arsenal.com/api?level=21)) 

Treat String Like A Pebble With Grapheme At Kotlin 🫧
</br>
</br>

## Why Pebble?
In Android, String is encoded in UTF-8, which means characters like emojis may not count as a single character.   
```kotlin
"🚀".length() == 2
```

To address this inconvenience, Pebble Is Coming!
</br>
</br>

## Gradle
Add the dependency below to your **module**'s `build.gradle` file:
```kotlin
dependencies {
    implementation("io.github.chattymin:pebble:0.0.2")
}
```
</br>
</br>

## How To Use?
```kotlin
"🚀".graphemeLength == 1
"🚀".isEmoji() == true
"Hello Pebble 🚀 Welcome!".containsEmoji() == true
"Hello🚀 This is Pebble 🫧 Welc🧑‍🧑‍🧒‍🧒ome".extractEmojis() == ["🚀", "🫧", "🧑‍🧑‍🧒‍🧒"]
```
In addition to this, various types of extension functions are also available.   
The goal is to cover all ranges officially provided by Kotlin.

You can explore and use various extension functions within the `Pebble` module :)
</br>
</br>

## Want To Contriubte
Contributions are always welcome!   
Feel free to make an issue everything that you want to contribute!    
Welcome **New Extensions**, **Performance Improvements**, and all ways of working with strings—**just like Pebble**!
</br>
</br>

## 👨‍💻 Contributors

[![contributors](https://contrib.rocks/image?repo=chattymin/pebble)](https://github.com/chattymin/pebble/contributors)
</br>
</br>

# License
```xml
Copyright 2025 patrick (Dongmin Park)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
