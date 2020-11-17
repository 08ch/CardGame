package dev.cal

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.*
import java.io.FileInputStream
import kotlin.random.Random

val colourList = listOf<String>("B", "R", "Y")
fun generateCard(): String {
    val card = "${colourList.random()}${Random.nextInt(1, 10)}"
    println(card)
    return card
}
class GameView: View("Card Game"){
    private var leftCard = Image(FileInputStream("src/main/resources/Spritesx10/placeholder.png"))
    private var rightCard = Image(FileInputStream("src/main/resources/Spritesx10/placeholder.png"))
    private val leftCardView = ImageView(leftCard)
    private val rightCardView = ImageView(rightCard)

    override val root: BorderPane = borderpane() {
        bottom {
            button("Draw next cards") {
                action {
                    leftCard = Image(FileInputStream("src/main/resources/Spritesx10/${generateCard()}.png"))
                    rightCard = Image(FileInputStream("src/main/resources/Spritesx10/${generateCard()}.png"))
                    leftCardView.image = leftCard
                    rightCardView.image = rightCard
                }
            }
        }
        center {
            add(leftCardView)
            add(rightCardView)
        }

    }
}