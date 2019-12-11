package com.darthvaldo.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRASS = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_DIRT = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_DAY = Game.spritesheet.getSprite(0, 0, 16,16);
	public static BufferedImage TILE_NIGHT = Game.spritesheet.getSprite(16,16*3,16,16);
	public static BufferedImage TILE_ROCK = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_SAND = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage TILE_SAND_2 = Game.spritesheet.getSprite(0,48, 16,16);
	public static BufferedImage TILE_SNOW = Game.spritesheet.getSprite(16,32,16,16);
	
	
	private BufferedImage sprite;
	protected int x,y;
	
	public boolean solid = false;

	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;

		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x,y - Camera.y,null);
	}

}
