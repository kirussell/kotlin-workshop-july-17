We will use Spark to handle HTTP calls

Add Spark dependency
---
in /lib/build.gradle
```
compile "com.sparkjava:spark-core:2.6.0"
compile 'org.slf4j:slf4j-simple:1.6.1' // for logging
```

Time to implement Java/Kotlin main method

Convert MyClass to kotlin
---
![](../images/step_10.jpg)

Write main function and configure build
---
~~class MyClass~~
```
fun main(args: Array<String>) {
    // TODO write code
}
```
in /lib/build.gradle
```
apply plugin: "application"
...
mainClassName = "com.example.MyClassKt"
```

Time for Hello world
---
code in `main` will look like this:
```
Spark.get("/kotlin/hello") { _, _ -> "Hello Kotlin User Group!" }
```
run `./gradlew :lib:run`
and
open http://localhost:4567/hello in browser




