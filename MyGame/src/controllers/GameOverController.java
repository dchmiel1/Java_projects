package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Main;

public class GameOverController {

	@FXML
	public void backMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/res/fxml/menuScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setScreen(pane);
	}
	
	@FXML
	public void exit() {
		System.exit(0);
	}
	
	public void setScreen(Pane pane) {
		Main.stackPane.getChildren().clear();
		Main.stackPane.getChildren().add(pane);
	}
}
