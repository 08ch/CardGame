package dev.cal

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import tornadofx.*

private val database = Database.connect("jdbc:sqlite:database/logins.db", "org.sqlite.JDBC").also {
    serializableTransaction {
        SchemaUtils.create(LoginsTable)
    }
}
class AppMain: App(LoginView::class)