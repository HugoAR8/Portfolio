package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.World;

public class Player extends Entity {
	
	public boolean right,up,left,down;

	public BufferedImage sprite_left;
	
	public static int life = 100;
	
	public int lastDir = 1;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		sprite_left = Game.spritesheet.getSprite(3*16,0,16,16);
	}

	public void tick() {
		depth = 1;
		
		

		if(right && World.isFree((int)(x+speed),this.getY(),z)) {
			x+=speed;
			lastDir = 1;
		}else if(left && World.isFree((int)(x-speed),this.getY(),z)) {
			x-=speed;
			lastDir = - 1;
		}
		else if(up && World.isFree(this.getX(),(int)(y-speed),z)) {
	
			
			y-=speed;
		}else if(down && World.isFree(this.getX(),(int)(y+speed),z)) {

		
			y+=speed;
		}
		
		verificaPegaCoin();
		
		if(Game.coins_count == Game.coins_actual) {
			//Ganhar o jogo !
			World.restartGame();
		}
		
		
	}
	public void verificaPegaCoin() {
		for(int i = 0; i < Game.entities.size(); i ++ ) {
			Entity current = Game.entities.get(i);
			if(current instanceof Coin) {
				if(isColliding(this,current)) {
					Game.coins_actual ++;
					Game.entities.remove(i);
					return;
				}
				
			}
		}
		
	}
	
	public void render(Graphics g) {
		if(lastDir == 1) {
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(lastDir == -1) {
			super.render(g);
		}
		
		
	}

}
