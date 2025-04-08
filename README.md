# Pebble

Treat String Like A Pebble With Grapheme At Kotlin 🫧
</br>
</br>

## Why Pebble?
In Android, String is encoded in UTF-8, which means characters like emojis may not count as a single character.   
```kotlin
"👍".length() == 2
```

To address this inconvenience, Pebble Is Coming!
</br>
</br>

## Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```kotlin
dependencies {
    implementation("io.github.chattymin:pebble:0.0.1")
}
```
</br>
</br>

## How To Use?
```kotlin
"👍".graphemeLength() == 1
```
</br>
</br>

## Want To Contriubte
Make a issue everything that you want to contribute!    
Welcome **New Extensions**, **Performance Improvements**, and all ways of working with strings—**just like Pebble**!
</br>
</br>

## 👨‍💻 Contributors

[![contributors](https://contrib.rocks/image?repo=chattymin/pebble)](https://github.com/chattymin/pebble/contributors)
