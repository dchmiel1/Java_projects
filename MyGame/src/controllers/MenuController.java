package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Main;

public class MenuController {
	
	@FXML
	public void play() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/res/fxml/heroChoiceScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setScreen(pane);
	}
	
	@FXML
	public void help() {
		
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
