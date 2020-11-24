package dev.cal

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import tornadofx.*
import java.io.File


class AppMain : App(LoginView::class) {
    init {
        File("database/").mkdir()
        File("database/logins.db").createNewFile()
        Database.connect("jdbc:sqlite:database/logins.db", "org.sqlite.JDBC")
        serializableTransaction {
            SchemaUtils.create(LoginsTable)
        }
    }
}