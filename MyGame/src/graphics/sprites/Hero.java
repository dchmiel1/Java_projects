package graphics.sprites;

import graphics.World;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;
import run.Runner;

public class Hero extends Sprite {

	private boolean airTime;
	private double timeToShoot = 0;
	private Image[] frames = new Image[1];
	private boolean bgMove;
	private int hp;
	private int timeToHit;

	public Hero(Image img, double x, double y, World world, Image img2) {
		super(img, x, y, world);
		this.frames[0] = img2;
		this.dir = Direction.RIGHT;
		this.airTime = false;
		this.bgMove = false;
		this.hp = 100;
		this.timeToHit = 0;
	}

	@Override
	public void update(double time) {
		updPosition(time);
		updTimeToShoot();
		if(timeToHit == 0)
			updHp();
		updTimeToHit();
	}
	
	@Override
	public void render() {
		if (dir == Direction.LEFT)
			gc.drawImage(frames[0], posX, posY);
		else
			gc.drawImage(img, posX, posY);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("", 20));
		gc.fillText("Hp: " + hp, 20, 20);
	}

	public void jump(double jumpPower) {
		if (!airTime) {
			this.speedY = jumpPower;
			airTime = true;
		}
	}

	public void setTimeToShoot(double time) {
		timeToShoot = time;
	}

	public double getTimeToShoot() {
		return timeToShoot;
	}

	public void updTimeToShoot() {
		if (timeToShoot != 0)
			timeToShoot--;
	}
	
	public void updTimeToHit() {
		if(timeToHit != 0)
			timeToHit --;
	}
	
	public void updPosition(double time) {
		double newY;
		double newX;

		// newY position
		if (airTime) {
			newY = posY - speedY * time + 8000 * time * time;
			speedY = speedY - 3500 * time;
			if(speedY <= -800)
				speedY = -800;
		} else
			newY = posY;

		// newX position
		newX = posX + speedX * time;
		
		//left block
		if(newX < 5) {
			newX = posX;
		}
			
		//new map lvl
		if(newX + width +10 > Main.WIDTH*Main.SCALE) {
			Runner.setNextMapLvl(true);
		}
		
		//high block
		if(newY < 0) {
			speedY = 0;
			newY = posY;
		}
		
		//check if standing
		if(!world.ifStanding((int)posX, (int)posY, width, height)) {
			airTime = true;
		}

		// check if collision with world
		if (!world.ifCollision((int) posX, (int) newY, width, height)) {
			posY = newY;
			if (!world.ifCollision((int) newX, (int) newY, width, height)) 			// if no Y and X collisions
				posX = newX;
			else {																	// if X collision
				if (newX - posX > 0)
					setRightCollision((int)newX);
				if (newX - posX < 0)
					setLeftCollision();
			}
		} else {
			if (newY - posY > 0) {
				setDownCollision((int)newY);
				airTime = false;
			}
			if (newY - posY < 0) {
				setUpCollision();
				airTime = true;
			}
			if (!world.ifCollision((int) newX, (int) posY, width, height))			// if Y collision
				posX = newX;
			else {																	// if X and Y collisions
				if (newX - posX > 0)
					setRightCollision((int)newX);
				if (newX - posX < 0)
					setLeftCollision();
			}
		}
		
		//game over
		if(posY + height +30 > Main.HEIGHT * Main.SCALE) {
			Runner.setGameOver(true);	
		}
				
		//bg move
		if(posX == newX)
			bgMove = true;
		else
			bgMove = false;
	}
	
	
	public boolean getBgMove() {
		return bgMove;
	}
	
	public void updHp() {
		for(int i = 0; i < Runner.level.monsters.size(); i++) {
			if(posX + width >= Runner.level.monsters.get(i).posX && 
				posX <= Runner.level.monsters.get(i).posX + Runner.level.monsters.get(i).width &&
				posY + height >= Runner.level.monsters.get(i).posY &&
				posY <= Runner.level.monsters.get(i).posY + Runner.level.monsters.get(i).height) {
				hp -= Runner.level.monsters.get(i).hpWhenCollision;
				timeToHit = 120; 
			}
		}
		
		if(hp <= 0)
			Runner.setGameOver(true);
	}
}
