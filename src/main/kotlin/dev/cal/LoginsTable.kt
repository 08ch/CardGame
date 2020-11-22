package dev.cal

import org.jetbrains.exposed.sql.Table

object LoginsTable: Table() {
    val id = integer("id").autoIncrement()
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)
    val username = text("username")
    val password = text("password")
    val wins = integer("wins")
}