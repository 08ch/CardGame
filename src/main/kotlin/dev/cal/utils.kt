package dev.cal

import javafx.scene.control.Alert
import javafx.stage.Window
import tornadofx.alert

// Utilities to reduce need for unnecessary code
fun showInvalidDetails(currentWindow: Window?) {
    alert(Alert.AlertType.ERROR, "Incorrect Login Details", "This user either doesn't exist or the password is incorrect", owner = currentWindow)
}