package graphics.sprites;

import graphics.World;
import javafx.scene.image.Image;
import main.Main;

public class Shot extends Sprite{
	
	public Shot(Image img, int x, int y, World world) {
		super(img, x, y, world);
		speedX = 700;
	}
	
	@Override
	public void update(double time) {
		if(dir == Direction.LEFT)
			posX = (int)(posX - speedX * time);
		else
			posX = (int)(posX + speedX * time);
	}
	
	public boolean gone() {
		if(posX + width > Main.WIDTH * Main.SCALE)
			return true;
		else
			return false;
	}
	
	@Override
	public void render(){
        gc.drawImage(img, posX, posY );
    }
}
