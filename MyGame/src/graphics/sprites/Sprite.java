package graphics.sprites;

import graphics.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	protected Image img;
	protected GraphicsContext gc;
	protected World world;
    protected double posX;
    protected double posY;    
    protected double speedX;
    protected double speedY;
    protected int width;
    protected int height;
    Direction dir;
    public static enum Direction{LEFT, RIGHT};
    
    public Sprite(Image img, double x, double y, World world){
    	this.img = img;
    	this.posX = x;
    	this.posY = y;
    	this.width = (int)img.getWidth();
    	this.height = (int)img.getHeight();
    	this.world = world;
    	this.gc = world.getGc();
    	this.speedX = 0;
    	this.speedY = 0;
    	render();
    }
    
    public abstract void update(double time);
    public abstract void render();
	
	public void setLeftCollision() {
		speedX = 0;
		int newPosX = (int) posX / world.getTileSize();
		posX = newPosX * world.getTileSize();
	}
	
	public void setRightCollision(int badPos) {
		speedX = 0;
		int xTile;
		xTile = (badPos + width) / world.getTileSize();
		posX = xTile * world.getTileSize() - width;
	}
	
	public void setUpCollision() {
		int newPosY = (int) posY / world.getTileSize();
		posY = newPosY * world.getTileSize();
		speedY = 0;
	}

	public void setDownCollision(int badPos) {
		int yTile = 0;
		yTile = (badPos +height) / world.getTileSize();
		posY = yTile * world.getTileSize() - height;
	}
	
	public void setSpeedX(double speedX) {
    	this.speedX = speedX;
    }
    
    public double getX() {
    	return posX;
    }
    
    public double getY() {
    	return posY;
    }
    
    public void setX(double x) {
    	this.posX = x;
    }
    
    public void setY(double y) {
    	this.posY = y;
    }
    
    public void setDirection(Direction dir) {
    	this.dir = dir;
    }
    
	public Direction getDirection() {
		 return dir;
	 }
    
}
