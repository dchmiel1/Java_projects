package graphics.sprites;

import graphics.World;
import javafx.scene.image.Image;

public class MonsterWorm extends Monster{
	
	private Image imageLeft;

	public MonsterWorm(Image img, double x, double y, World world, Image img2) {
		super(img, x, y, world);
		dir = Direction.RIGHT;
		imageLeft = img2;
		speedX = 200;
		this.hpWhenCollision = 20;
	}

	@Override
	public void update(double time) {
		double newX = speedX * time + posX;
		
		//check if rotate
		if(world.ifRotate((int)newX, (int)posY, width, height, dir)){
			if(dir == Direction.RIGHT)
				dir = Direction.LEFT;
			else
				dir = Direction.RIGHT;
			speedX = -speedX;
			newX = speedX * time + posX;
		}
		posX = newX;
	}

	@Override
	public void render() {
		if (dir == Direction.LEFT)
			gc.drawImage(imageLeft, posX, posY);
		else
			gc.drawImage(img, posX, posY);
	}

}
