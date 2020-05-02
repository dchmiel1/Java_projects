package controllers;

import java.io.IOException;

import gameStates.Level1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;

public class HeroChoiceController {

	@FXML
	Button playButton;
	
	@FXML
	ImageView chmielciuImg;
	
	@FXML
	ImageView gorziImg;
	
	Glow glow = new Glow();
	
	public char choice;
	
	public void initialize() {
		playButton.setDisable(true);
		glow.setLevel(0.9);
	}
	
	@FXML
	public void play() {
		Canvas canvas = new Canvas(Main.WIDTH * Main.SCALE, Main.HEIGHT * Main.SCALE);
		Main.stackPane.getChildren().clear();
		Main.stackPane.getChildren().add(canvas);
		Level1 level1 = new Level1(canvas, choice);
		level1.start();
	}
	
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
	
	public void setScreen(Pane pane) {
		Main.stackPane.getChildren().clear();
		Main.stackPane.getChildren().add(pane);
	}
	
	@FXML
	public void chmielciuChosen() {
		playButton.setDisable(false);
		gorziImg.setEffect(null);
		chmielciuImg.setEffect(glow);
		choice = 'c';
	}
	
	@FXML
	public void gorziChosen() {
		playButton.setDisable(false);
		chmielciuImg.setEffect(null);
		gorziImg.setEffect(glow);
		choice = 'g';
	}
}
