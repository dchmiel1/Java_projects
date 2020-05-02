package gameStates;

import java.util.ArrayList;

import graphics.Background;
import graphics.World;
import graphics.sprites.Hero;
import graphics.sprites.Monster;
import graphics.sprites.Shot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import run.Runner;

public abstract class GameState {
	
	protected Hero hero;
	protected Background bg;
	protected World world;
	protected GraphicsContext gc;
	protected Runner runner;
	protected Canvas canvas;
	public ArrayList<Monster> monsters = new ArrayList<Monster>();
	public static ArrayList<Shot> shots = new ArrayList<Shot>();
	
	public abstract void update(double elapsedTime);
	public abstract void render();
	public abstract void setStartingSprites(int mapLvl);
	public abstract void nextMapLvlSet();
	public abstract Hero getHero();
	public abstract Background getBg();
	public abstract World getWorld();
	public abstract void start();
	
}
