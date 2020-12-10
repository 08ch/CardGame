package dev.cal

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun <T> serializableTransaction(block: Transaction.() -> T): T {
    return transaction(Connection.TRANSACTION_SERIALIZABLE, 1, null, block)
}
// Adds user to database
fun addUser(username: String, password: String): Boolean {
    return serializableTransaction {
        if (LoginsTable.select {
                    LoginsTable.username eq username
                }.firstOrNull() == null) {
            LoginsTable.insert {
                it[LoginsTable.username] = username
                it[LoginsTable.password] = password
                it[LoginsTable.gamesPlayed] = 0
            }
            true
        } else false

        //TODO: Hash and salt passwords
    }
}
// Checks if a user exists
fun getUser(username: String): ResultRow? {
    return serializableTransaction {
        LoginsTable.select{
            LoginsTable.username eq username
        }.firstOrNull()
    }
}

// Adds 1 game played to the specified user
fun addGamePlayed(username: String): Unit = serializableTransaction {
    LoginsTable.update({LoginsTable.username eq username})  {
        it.update(gamesPlayed) { gamesPlayed + 1}
    }
}
