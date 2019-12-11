package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.World;

public class Player extends Entity {
	
	public boolean isPressed = false;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
	}

	public void tick() {
		depth = 2;
		if(!isPressed) {
			y+=2;
		}else {
			if(y > 0) {
				y-=3;
			}
		}
		
		if(y > Game.HEIGHT) {
			World.restartGame();
		}
		
		
		//testar colisão 
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e != this){
				if(Entity.isColliding(this, e)) {
					//Game OVO
					World.restartGame();
					return;
				}
				
			}
		}
	
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(!isPressed) {
			g2.rotate(Math.toRadians(20),this.getX() + width/2,this.getY() + height/2);
			g2.drawImage(sprite,this.getX(),this.getY(),null);
			g2.rotate(Math.toRadians(-20),this.getX() + width/2,this.getY() + height/2);
		}else {
			//g2.rotate(Math.toRadians(-20),this.getX() + width/2,this.getY() + height/2);
			g2.drawImage(sprite,this.getX(),this.getY(),null);
			//g2.rotate(Math.toRadians(20),this.getX() + width/2,this.getY() + height/2);
		}
	}

}
