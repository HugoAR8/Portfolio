package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.world.World;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	
	public int life = 1;
	
	public int gravity = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(World.isFree((int)x,(int)(y+gravity)-4)){
			y+=gravity;
			
		}else {
			if(right) {
				if(World.isFree((int)(x+speed),(int)y - 4)) {
					x+=speed;
					if(World.isFree((int)(x+16),(int)y+1)) {
						right = false;
						left = true;
					}
					}else {
						right = false;
						left = true;
					}
				}
				
			} if (left) {
				if(World.isFree((int)(x-speed),(int)y - 4)){
					x-=speed;
					if(World.isFree((int)(x-16),(int)y+1)) {
						right = true;
						left = false;
					}
				}else {
					right = true;
					left = false;
				}
			}
		}
	
	
	
	public void render(Graphics g) {
		if(right) {
			sprite = Entity.ENEMY1_RIGHT;
		}else if(left) {
			sprite = Entity.ENEMY1_LEFT;
		}
		super.render(g);
	}

}
