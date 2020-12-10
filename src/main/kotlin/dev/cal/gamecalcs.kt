package dev.cal

// List of all available card colours
val colourList = listOf<String>("B", "R", "Y")

// Generates a complete deck, once only
val completeDeck = mutableSetOf<String>().apply {
    for (colour in colourList) {
        for (number in 1..10) {
            add("$colour$number")
        }
    }
}

// All game control related functions
class Game {
    // Generates a copy of the previously generated list, but also shuffles it at the same time
    val currentDeck = completeDeck.toMutableList().apply { shuffle() }
    var p1points = 0
    var p2points = 0

    // Draws a random card from the deck
    fun drawCard(): String {
        return currentDeck.removeFirst()
    }

    // Returns winning player from a two card comparison
    fun compare(p1colour: Char, p1num: String, p2colour: Char, p2num: String) {
        var winner = "comparison error"
        // Compares numbers if colours are the same
        if (p1colour == p2colour) {
            if (p1num.toInt() > p2num.toInt()) {
                winner = "p1"
            } else if (p1num.toInt() < p2num.toInt()) {
                winner = "p2"
            } else {
                winner = "none"
            }
        }

        //Compares the colours to see who wins TODO: Make better version of this later
        when (p1colour) {
            'R' -> {
                if (p2colour == 'B') {
                    winner = "p1"
                } else if (p2colour == 'Y') {
                    winner = "p2"
                }
            }
            'B' -> {
                if (p2colour == 'Y') {
                    winner = "p1"
                } else if (p2colour == 'R') {
                    winner = "p2"
                }
            }
            'Y' -> {
                if (p2colour == 'R') {
                    winner = "p1"
                } else if (p2colour == 'B') {
                    winner = "p2"
                }
            }
        }
        if (winner == "p1") {
            p1points++
        } else {
            p2points++
        }
    }

    // Gets the winner based off points comparisons
    fun getWinner():String {
        if (p1points > p2points) {
            return "Player 1"
        }
        else {
            return "Player 2"
        }
    }
}
