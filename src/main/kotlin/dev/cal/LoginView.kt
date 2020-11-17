package dev.cal
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TabPane
import tornadofx.*


/*
class LoginView: View() {
    override val root = vbox {
        button("Press me")
        label("Waiting")
    }
}
 */

// Login view
class LoginView: View() {
    private val controller: SubmitController by inject()
    private val username = SimpleStringProperty()
    private val password = SimpleStringProperty()
    override val root = form {
        fieldset {
            username.value = ""
            password.value = ""
            field("Username") {
                textfield(username)
            }
            field("Password") {
                passwordfield(password)
            }
            hbox {
                button("Submit") {
                    hboxConstraints {
                        marginRight = 20.0
                    }
                    action {
                        if (username.value == "" || password.value == "") {
                            replaceWith<InvalidView>()
                        }
                        else {
                            replaceWith<GameView>()
                        }
                        controller.submitUsername(username.value ?: "")
                        controller.submitPassword(password.value)
                        username.value = ""
                        password.value = ""
                    }
                }
                button("Settings") {
                    action {
                        replaceWith<SettingsView>()
                    }
                }

            }
        }
    }
}

// loginView submit controller
class SubmitController: Controller() {
    fun submitUsername(inputValue: String) {
        println("Username = $inputValue")
    }
    fun submitPassword(inputValue: String) {
        println("Password = $inputValue")
    }
}

// Settings view

class SettingsView: View() {
    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("Settings") {
            vbox {
                label("PlaceholderS")
            }
        }
        tab("Themes") {
            vbox {
                label("PlaceholderT")
            }
        }
    }
}

// Invalid view
class InvalidView: View() {
    override val root = vbox {
        label("Incorrect login details")
        button("Go back") {
            vboxConstraints { marginTop = 20.0 }
            action {
                replaceWith<LoginView>()
            }
        }
    }
}

// Game view