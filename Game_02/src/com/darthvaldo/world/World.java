package com.darthvaldo.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.darthvaldo.entities.Coin;
import com.darthvaldo.entities.Enemy;
import com.darthvaldo.entities.Entity;
import com.darthvaldo.entities.Player;
import com.darthvaldo.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public static Enemy enemy;
	
	
	public World(String path) {		
		try {
			BufferedImage map =ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0,map.getWidth()); 
			for(int xx=0; xx< map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual =pixels[xx + (yy*map.getWidth())] ;
					tiles[xx + ( yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					if(pixelAtual == 0xFF000000) {
						//Floor
						tiles[xx + ( yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF) {
						//Wall
						tiles[xx + ( yy*WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0026FF) { 
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFFF1FF08) {
						//Coin
						Coin coin = new Coin(xx*16,yy*16,16,16,0,Entity.COIN_SPRITE);
						Game.entities.add(coin);
						Game.coins_count ++;
					}else if(pixelAtual == 0xFF53FF00) {
						//Green enemy
						Enemy enemy = new Enemy(xx*16,yy*16,16,16,1,Entity.ENEMY1);
						Game.entities.add(enemy);
					}else if(pixelAtual == 0xFFFD0000) {
						//Red enemy
						Enemy enemy = new Enemy(xx*16,yy*16,16,16,1,Entity.ENEMY2);
						Game.entities.add(enemy);
					}else if(pixelAtual == 0xFFFF00B4) {
						//Green enemy
						Enemy enemy = new Enemy(xx*16,yy*16,16,16,1,Entity.ENEMY3);
						Game.entities.add(enemy);
					}
				}
			}
		}catch (IOException e) {
		e.printStackTrace();
		}
			
			
	}
	
	public static boolean isFree(int xnext, int ynext,int zplayer) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext/ TILE_SIZE;
		int y3 = (ynext +TILE_SIZE - 1 )/ TILE_SIZE;
		
		int x4 = (xnext +TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext +TILE_SIZE - 1) / TILE_SIZE;
		
			if(!((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile))) {
				return true;
			}if(zplayer>0) {
				return true;
			}
			return false;
	}
	
	public static void restartGame() {
		Game.player = new Player(0,0,16,16,2,Game.spritesheet.getSprite(32,0,16,16));
		Game.entities.clear();
		Game.entities.add(Game.player);
		Game.coins_actual = 0;
		Game.world = new World("/level1.png");
		return;
	}
	
	
	public void render(Graphics g) {
		int xstart = Camera.x >>4;
		int ystart = Camera.y >>4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart;xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0  || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			}
		}
	}

}
