package graphics.sprites;

import graphics.World;
import javafx.scene.image.Image;

public abstract class Monster extends Sprite{
	
	protected int hpWhenCollision;

	public Monster(Image img, double x, double y, World world) {
		super(img, x, y, world);
	}
	@Override
	public abstract void update(double time);

	@Override
	public abstract void render();

}
