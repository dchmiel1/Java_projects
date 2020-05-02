package run;

import java.io.IOException;

import gameStates.GameState;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import keyboard.Keyboard;
import main.Main;

public class Runner {

	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Keyboard keyboard;
	private static boolean gameOver;
	private static boolean nextMapLvl;
	public static GameState level;
	
	enum Direction{LEFT, RIGHT};
	
	public Runner(Canvas canvas) {
		this.scene = Main.scene;
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		Runner.gameOver = false;
		Runner.nextMapLvl = false;
	}
	
	public void run() {
		LongValue lastNanoTime = new LongValue( System.nanoTime() );
		keyboard = new Keyboard(level.getHero(), level.getBg(), level.getWorld(), scene, canvas);
		new AnimationTimer() {
			public void handle(long currTime) {
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				double elapsedTime = (currTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currTime;
				keyboard.keyboardController();
				if(gameOver) {
					this.stop();
					gameOver();
				}
				if(nextMapLvl) {
					nextMapLvl = false;
					level.nextMapLvlSet();
				}
				level.update(elapsedTime);
				level.render();
			}
		}.start();
	}
	
	public class LongValue{
		public long value;
		
		public LongValue(long time){
			this.value = time;
		}
	}

	public static void setGameOver(boolean gameOver) {
		Runner.gameOver = true;
	}
	
	public void gameOver() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/res/fxml/gameOverScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.stackPane.getChildren().clear();
		Main.stackPane.getChildren().add(pane);
	}
	
	public static void setNextMapLvl(boolean nextMapLvl) {
		Runner.nextMapLvl = nextMapLvl;
	}
	
	public void setLevel(GameState level) {
		Runner.level = level;
	}
	
}
