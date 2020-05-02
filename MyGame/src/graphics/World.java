package graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import graphics.sprites.Sprite.Direction;
import javafx.scene.canvas.GraphicsContext;
import main.Main;

public class World {
	
	private int tileSize;
	private int numRows;
	private int numCols;
	private GraphicsContext gc;
	private static int mapLvl;
	
	private char map[][];
	private Tile[][] tiles;
	
	//private int x;
	//private int y;
	//private int width;
	//private int height;
	
	
	private double tween;
	private int numTilesAcross;
	
	
	//drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public World(int tileSize, GraphicsContext gc) {
		numRows = Main.HEIGHT * Main.SCALE / tileSize;
		numCols = Main.WIDTH * Main.SCALE / tileSize;
		map = new char[numRows][numCols];
		tiles = new Tile[numRows][numCols];
		this.gc = gc;
		this.tileSize = tileSize;
		World.mapLvl = 0;
	}
	
	public void loadTiles() {
		try {
			for(int i = 0; i < numRows; i++)
				for(int j = 0; j < numCols; j++) {
					tiles[i][j] = new Tile(map[i][j] - 48);
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			char a;
			File file = new File("C:\\Users\\chmie\\Desktop\\Eclipse-projekty\\MyGame\\src\\res\\maps\\map.txt");
			FileReader fr=new FileReader(file);   //Creation of File Reader object
		    BufferedReader br=new BufferedReader(fr);
		    for(int i = 0; i < mapLvl*numRows* (numCols+2); i++) {
		    	a = (char) br.read();
		    }
		    	
			for(int row = 0; row <numRows; row++) {
				for(int col = 0; col < numCols; col ++) {
					a = (char) br.read();
					if( a > 47)
						map[row][col] = a;
					else
						if(col != numCols -1)
							col --;
						else
							row--;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		mapLvl ++;
	}
	
	public void render() {
		for(int row = 0; row <numRows; row++) {
			for(int col = 0; col < numCols; col ++) {
				if(map[row][col] > 48) {
					tiles[row][col].render(gc, col, row);
				}
				
			}
		}
	}
	
	public boolean ifCollision(int px, int py, int w, int h) {
		int yTilesToCheck = 0;
		int x = px/ tileSize;
		int y = py / tileSize;
		
		int pixelX = x * tileSize;
		int pixelY = y * tileSize;
		if(h % tileSize == 0 && py == pixelY)
			yTilesToCheck = h / tileSize;
		else
			if(h% tileSize == 0)
				yTilesToCheck = h / tileSize +1;
			else
				yTilesToCheck = h / tileSize +2;
		int xTilesToCheck = w / tileSize +2;
		for(int i = 0; i < yTilesToCheck; i ++)
			for(int j = 0; j < xTilesToCheck; j++) {
				if( y + i >= numRows || x + j >= numCols)
					return false;
				if(tiles[y + i][x + j].getCollision() && px + w > pixelX + j*tileSize && px < pixelX + j*tileSize + tileSize && py + h > pixelY + i*tileSize && py < pixelY + i*tileSize+ tileSize)
					return true;
			}
		return false;
		
	}
	
	public boolean ifStanding(int px, int py, int w, int h) {
		int x = px /tileSize;
		int y = (py + h)/tileSize;
		int pixelX = x * tileSize;
		int tilesToCheck;
		if( w % 40 == 0 && px == pixelX)
			tilesToCheck = w / tileSize;
		else
			if(px + w < pixelX + tileSize*2)
				tilesToCheck = w / tileSize +1;
			else
				tilesToCheck = w / tileSize +2;
	
		for(int i = 0; i < tilesToCheck; i++) {
			if(y >= numRows)
				return true;
			if(tiles[y][x+i].getCollision())
				return true;
		}
		return false;
	}
	
	public boolean ifRotate(int px, int py, int w, int h, Direction dir) {
		int x = px / tileSize;
		int y = (py + h) / tileSize;
		if(dir == Direction.LEFT) {
			if(x < 0 || x > numCols -1 ||!tiles[y][x].getCollision() )
				return true;
		}
		else
			if(x+1 < 0 || x+1 > numCols -1 || !tiles[y][x+1].getCollision() )
				return true;
		
		return false;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getMapLvl(){
		return mapLvl;
	}
}
