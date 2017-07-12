package com.example

import spark.Spark

fun main(args: Array<String>) {
    Spark.get("/kotlin/hello") { _, _ -> "Hello Kotlin User Group!" }
}
