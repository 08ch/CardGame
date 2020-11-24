package dev.cal

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun <T> serializableTransaction(block: Transaction.() -> T): T {
    return transaction(Connection.TRANSACTION_SERIALIZABLE, 1, null, block)
}

fun addUser(username: String, password: String): Boolean {
    return serializableTransaction {
        if (LoginsTable.select {
                    LoginsTable.username eq username
                }.firstOrNull() == null) {
            LoginsTable.insert {
                it[LoginsTable.username] = username
                it[LoginsTable.password] = password
                it[LoginsTable.wins] = 0
            }
            true
        } else false

        //TODO: Hash and salt passwords
    }
}

//fun verifyLogin(username: String, password: String){
//
//}