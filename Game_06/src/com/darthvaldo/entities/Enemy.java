package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.FloorTile;
import com.darthvaldo.world.Tile;
import com.darthvaldo.world.WallTile;
import com.darthvaldo.world.World;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	
	public double life = 100;
	public double maxLife = 100;
	
	
	public BufferedImage sprite1, sprite2;
	
	public int dir = 1;
	
	public int gravity = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite1,BufferedImage sprite2) {
		super(x, y, width, height, speed, null);
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	}
	
	public void tick() {
		if(World.isFree((int)x,(int)(y+gravity))){
			y+=gravity;
			
		}
		
		if(dir == 1) {
			if(World.isFree((int)(x+speed), (int)y)) {
					x+=speed;
					
			}else{
				int xnext = (int)((x+speed)/16) + 1;
				int ynext = (int)(y /16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					
					World.tiles[xnext+ynext*World.WIDTH]= new FloorTile(xnext*16,ynext*16,Tile.TILE_DAY);
				}
				dir = -1;
			}
			
		}else if(dir == -1) {
			if(World.isFree((int)(x-speed), (int)(y))) {
				x-=speed;
				
			}else {
				int xnext = (int)((x- speed)/16);
				int ynext = (int)(y /16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					
					World.tiles[xnext+ynext*World.WIDTH]= new FloorTile(xnext*16,ynext*16,Tile.TILE_DAY);
				}
				dir = 1;
			}
		}
		if(life <= 0) {
			Game.entities.remove(this);
			return;
		}
	}
	
	
	
	public void render(Graphics g) {
		if(dir == 1) {
			sprite = sprite1;
		}else if(dir == -1) {
			sprite = sprite2;
		}
		super.render(g);
		
		int curLife = (int)((life/maxLife) * 20);
		g.setColor(Color.red);
		g.fillRect((this.getX()) - Camera.x, (this.getY() + 16) - Camera.y, 20,3);
		g.setColor(Color.green);
		g.fillRect((this.getX()) - Camera.x,(this.getY() + 16) - Camera.y,curLife,3);
	}

}
