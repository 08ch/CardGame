package dev.cal

// Compares the two drawn cards and return which one wins the round, returns 'none' if draw
fun compare(p1colour: String, p1num: String, p2colour: String, p2num: String): String {
    var winner = "comparison error"
    // Compares numbers if colours are the same
    if (p1colour == p2colour) {
        if (p1num > p2num) {
            winner = "p1"
        }
        else if (p1num < p2num) {
            winner = "p2"
        }
        else {winner = "none"}
    }

    //Compares the colours to see who wins ---Make better version of this later---
    when (p1colour) {
        "red" -> {
            if (p2colour == "black") {winner = "p1"} else if (p2colour == "yellow") {winner = "p2"}
        }
        "black" -> {
            if (p2colour == "yellow") {winner = "p1"} else if (p2colour == "red") {winner = "p2"}
        }
        "yellow" -> {
            if (p2colour == "red") {winner = "p1"} else if (p2colour == "black") {winner = "p2"}
        }
    }
    return winner
}