package com.example

import com.google.gson.Gson
import spark.Spark

val dao = MembersDao()
val gson = Gson()

fun Any?.toJson(): String = gson.toJson(this)

fun main(args: Array<String>) {
    helloWorld()
    Spark.get("kotlin/members") { _, _ -> dao.all().toJson() }
}

private fun helloWorld() {
    Spark.get("/kotlin/hello") { _, _ -> "Hello Kotlin User Group!" }
}

data class Member(val name: String, val paid: Int)

class MembersDao {
    fun all() = javaClass.classLoader
            .getResource("members.db")
            .readText()
            .reader()
            .buffered()
            .readLines()
            .map { it.split(" ") }
            .map { Member(it[0], it[1].toInt()) }
}