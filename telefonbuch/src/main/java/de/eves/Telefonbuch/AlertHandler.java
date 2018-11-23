package de.eves.Telefonbuch;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {

	public void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Alert");
		alert.setHeaderText("ERROR");
		alert.setContentText("You have to enter a name and surname");
		alert.showAndWait();
	}
}
