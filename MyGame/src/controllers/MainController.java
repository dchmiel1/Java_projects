package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {

	@FXML
	private StackPane mainStackPane;
	
	@FXML
	public void initialize() {
		setMenu();
	}
	
	public void setMenu() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/res/fxml/menuScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setScreen(pane);
	}
	
	public void setScreen(Pane pane) {
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
}
