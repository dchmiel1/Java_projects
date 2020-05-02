package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static int SCALE = 8;
	public static int WIDTH = 160;
	public static int HEIGHT = 90;
	public static Scene scene;
	public static StackPane stackPane;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/res/fxml/mainScreen.fxml"));
		stackPane = loader.load();
		scene = new Scene(stackPane, WIDTH * SCALE, HEIGHT * SCALE);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Game");
		stage.show();
	}

}
