package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile {
	
	private Image image;
	private boolean collision;
	private int type;
	
	public Tile(int type){
		this.type = type;
		switch(type) {
		case 0:
			image = null;
			collision = false;
			break;
		case 1:
			this.image = new Image(getClass().getResource("/res/img/tiles/grassLeft.png").toExternalForm());
			collision = true;
			break;
		case 2:
			image = new Image(getClass().getResource("/res/img/tiles/grassMiddle.png").toExternalForm());
			collision = true;
			break;
		case 3:
			image = new Image(getClass().getResource("/res/img/tiles/grassRight.png").toExternalForm());
			collision = true;
			break;
		case 4:
			image = new Image(getClass().getResource("/res/img/tiles/grassSingle.png").toExternalForm());
			collision = true;
			break;
		case 5:
			image = new Image(getClass().getResource("/res/img/tiles/dirt.png").toExternalForm());
			collision = true;
			break;
		default:
			image = null;
			collision = false;
		}
		
	}
	
	public void render(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x*40, y*40);
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getCollision() {
		return collision;
	}
}
