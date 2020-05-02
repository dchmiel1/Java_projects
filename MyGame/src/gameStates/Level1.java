package gameStates;

import graphics.Background;
import graphics.World;
import graphics.sprites.Hero;
import graphics.sprites.MonsterWorm;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import run.Runner;

public class Level1 extends GameState{
	
	private MonsterWorm wormMonster;
	private char choice;
	
	public Level1(Canvas canvas, char choice) {
		this.choice = choice;
		this.gc = canvas.getGraphicsContext2D();
		this.canvas = canvas;
	}
	
	@Override
	public void start() {
		world = new World(40, gc);
		world.loadMap();
		world.loadTiles();
		setStartingSprites(world.getMapLvl());
		runner = new Runner(canvas);
		runner.setLevel(this);
		runner.run();
	}

	@Override
	public void update(double elapsedTime) {
		bg.update();
		hero.update(elapsedTime);
		for(int i = 0; i < monsters.size(); i++)
			monsters.get(i).update(elapsedTime);
			
		for(int i = 0; i < shots.size(); i++) {
			if(shots.get(i).gone()) {
				shots.get(i);
				shots.remove(i);
			}
			else
				shots.get(i).update(elapsedTime);
		}
	}

	@Override
	public void render() {
		bg.draw(gc);
		hero.render();
		world.render();
		for(int i = 0; i < monsters.size(); i++)
			monsters.get(i).render();
		for(int i = 0; i < shots.size(); i ++)
			shots.get(i).render();
	}

	@Override
	public void setStartingSprites(int mapLvl) {
			try {
				switch(mapLvl) {
				case 1:
					if(choice == 'c')
						hero = new Hero(new Image(getClass().getResource("/res/img/hero/heroM_R_53x158.png").toExternalForm()), 50, 150, world, new Image(getClass().getResource("/res/img/hero/heroM_L_53x158.png").toExternalForm()));
					else
						hero = new Hero(new Image(getClass().getResource("/res/img/hero/heroF_R_46x158.png").toExternalForm()), 50, 150, world, new Image(getClass().getResource("/res/img/hero/heroF_L_46x158.png").toExternalForm()));
					wormMonster = new MonsterWorm(new Image(getClass().getResource("/res/img/worm_R.png").toExternalForm()), 600, 210, world, new Image(getClass().getResource("/res/img/worm_L.png").toExternalForm()));
					monsters.add(wormMonster);
					bg = new Background("/res/img/bg/bg.png", 0.1, hero);
					bg.draw(gc);
					break;
				case 2:
					monsters.clear();
					wormMonster = new MonsterWorm(new Image(getClass().getResource("/res/img/worm_R.png").toExternalForm()), 720, 650, world, new Image(getClass().getResource("/res/img/worm_L.png").toExternalForm()));
					monsters.add(wormMonster);
					break;
				case 3:
					monsters.clear();
					break;
				default:
					Runner.setGameOver(true);
					break;
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void nextMapLvlSet() {
		world.loadMap();
		world.loadTiles();
		hero.setX(50);
		hero.setY(300);
		setStartingSprites(world.getMapLvl());
	}
	
	@Override
	public Background getBg() {
		return bg;
	}
	
	@Override
	public World getWorld() {
		return world;
	}
	
	@Override
	public Hero getHero() {
		return hero;
	}
	
}
