package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;

public class Player extends Entity {
	
	
	public boolean right,left;
	
	public boolean isShooting = false;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		
		
	}

	public void tick() {
		if(right) {
			x+=speed;
		}else if(left) {
			x-= speed;
		}
		
		if(x >= Game.WIDTH) {
			x = -16;
		}else if(x+16 < 0) {
			x = Game.WIDTH;
		}
		
		//sistema de tiro.
		
		if(isShooting) {
			isShooting = false;
			int xx = this.getX()+6;
			int yy = this.getY() ;
			Bullet bullet = new Bullet(xx,yy,3,3,3,null);
			Game.entities.add(bullet);
		}
	}
	
	public void collisionEnemy() {

	}
	

	



}
