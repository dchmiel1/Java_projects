package keyboard;

import java.util.ArrayList;

import gameStates.Level1;
import graphics.Background;
import graphics.World;
import graphics.sprites.Hero;
import graphics.sprites.Shot;
import graphics.sprites.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Keyboard {

	private ArrayList<String> input = new ArrayList<String>();
	private Hero hero;
	private Background bg;
	private World world;
	private Scene scene;
	private Canvas canvas;
	
	public Keyboard(Hero hero, Background bg, World world, Scene scene, Canvas canvas) {
		this.hero = hero;
		this.bg = bg;
		this.world = world;
		this.scene = scene;
		this.canvas = canvas;
		setKeyboard();
	}
	
	public void keyboardController() {
		if(!input.contains("D") && !input.contains("A")) {
			hero.setSpeedX(0);
			bg.setVector(0);
		}
		if(input.contains("W"))
			hero.jump(1310);
		//if(input.contains("S"))
			//hero.setSpeedY(0);
		if(input.contains("D")) {
			hero.setSpeedX(300);
			hero.setDirection(Sprite.Direction.RIGHT);
			bg.setVector(-0.3);
		}
		if(input.contains("A")) {
			hero.setSpeedX(-300);
			hero.setDirection(Sprite.Direction.LEFT);
			bg.setVector(0.3);
				
		}
		if(input.contains("SPACE")) {
			if(hero.getTimeToShoot() == 0) {
				int shotX = (int) hero.getX();
				int shotY = (int) hero.getY() + 40;
				Shot shot = new Shot(new Image(getClass().getResource("/res/img/otherSprites/pocisk.png").toExternalForm()), shotX, shotY, world);
				shot.setDirection(hero.getDirection());
				Level1.shots.add(shot);
				hero.setTimeToShoot(30);
			}
		}
	}
	
	public void setKeyboard(){
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{ 
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				String code = key.getCode().toString();
                if ( !input.contains(code) )
                    input.add( code );
		});
		scene.addEventFilter(KeyEvent.KEY_RELEASED, key ->{
			String code = key.getCode().toString();
            input.remove( code );
		});
	}
}
