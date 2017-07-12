package com.example

import com.google.gson.Gson
import spark.Spark
import spark.Spark.halt

val dao = MembersDao()
val gson = Gson()

fun Any?.toJson(): String = gson.toJson(this)

fun main(args: Array<String>) {
    helloWorld()
    membersList()
    searchByUserName()
    searchMembers()
}

private fun searchMembers() {
    Spark.get("kotlin/search/members") { req, _ ->
        dao.members(req.queryParams("query") ?: "").toJson()
    }
}

private fun searchByUserName() {
    Spark.get("kotlin/members/:user") { req, _ ->
        dao.search(req.params("user"))?.toJson() ?: halt(404, "User not found")
    }
}

private fun membersList() {
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

    fun search(name: String): Member? = all().find { it.name == name }

    fun members(query: String) = all().filter { it.name.startsWith(query) }
}