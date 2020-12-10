package dev.cal

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.control.TabPane
import tornadofx.*

var currentUsername = ""

/*
class LoginView: View() {
    override val root = vbox {
        button("Press me")
        label("Waiting")
    }
}
 */

// Login view
class LoginView : View() {
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
                            showInvalidDetails(currentWindow)
                        } else {
                            if (controller.submitDetails(username.value ?: "", password.value ?: "")) {
                                currentUsername = username.value
                                replaceWith<GameView>()
                            } else {
                                showInvalidDetails(currentWindow)
                            }

                            username.value = ""
                            password.value = ""

                        }

                    }
                }
                button("Register") {
                    hboxConstraints {
                        marginRight = 20.0
                    }
                    action {
                        replaceWith<RegisterView>()
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
class SubmitController : Controller() {
    fun submitDetails(username: String, password: String): Boolean {
        val user = getUser(username) ?: return false
        return user[LoginsTable.password] == password
    }
}

// Settings view

class SettingsView : View() {
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
class InvalidView : View() {
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

// Register new user
class RegisterView : View() {
    private val controller: RegisterUserController by inject()
    private val newUsername = SimpleStringProperty()
    private val newPassword = SimpleStringProperty()
    override val root = form {
        fieldset {
            newUsername.value = ""
            newPassword.value = ""
            field("Pick a username") {
                textfield(newUsername)
            }
            field("Pick a password") {
                passwordfield(newPassword)
            }
            hbox {
                button("Register") {
                    hboxConstraints {
                        marginRight = 20.0
                    }
                    action {
                        if (newUsername.value.isNotEmpty() && newPassword.value.isNotEmpty()) {

                            val success = controller.registerUser(newUsername.value, newPassword.value)
                            if (success) {
                                replaceWith<LoginView>()
                                newUsername.value = ""
                                newPassword.value = ""
                            } else {
                                alert(
                                    Alert.AlertType.ERROR,
                                    "Existing User",
                                    "A user with that username already exists",
                                    owner = currentWindow
                                )
                            }
                        }

                    }
                }
                button("Back") {
                    hboxConstraints {
                        marginRight = 20.0
                    }
                    action {
                        replaceWith<LoginView>()
                    }
                }
            }
        }
    }
}

class RegisterUserController : Controller() {
    fun registerUser(username: String, password: String): Boolean {
        return addUser(username, password)
    }
}