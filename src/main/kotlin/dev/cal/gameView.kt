package dev.cal

import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.FileInputStream

// Game view
class GameView: View("Card Game"){
    private var leftCard = Image(FileInputStream("src/main/resources/Spritesx10/placeholder.png"))
    private var rightCard = Image(FileInputStream("src/main/resources/Spritesx10/placeholder.png"))
    private val leftCardView = ImageView(leftCard)
    private val rightCardView = ImageView(rightCard)
    private val gameInst = Game()
    override val root: BorderPane = borderpane() {
        bottom {
            // Button to draw the next cards
            button("Draw next cards") {
                action {
                    val p1Drawn = gameInst.drawCard()
                    val p2Drawn = gameInst.drawCard()
                    leftCard = Image(FileInputStream("src/main/resources/Spritesx10/$p1Drawn.png"))
                    rightCard = Image(FileInputStream("src/main/resources/Spritesx10/$p2Drawn.png"))
                    leftCardView.image = leftCard
                    rightCardView.image = rightCard
                    gameInst.compare(p1Drawn[0], p1Drawn.substring(1), p2Drawn[0], p2Drawn.substring(1))
                    // Checks if game is over
                    if (gameInst.currentDeck.isEmpty()){
                        alert(Alert.AlertType.CONFIRMATION, "${gameInst.getWinner()} wins!", "${gameInst.getWinner()} has won the game!", owner = currentWindow)
                        addGamePlayed(currentUsername)
                        replaceWith<LoginView>()
                    }

                }
            }
        }
        // Displays left and right cards
        left {
            add(leftCardView)
        }
        right {
            add(rightCardView)
        }

    }
}