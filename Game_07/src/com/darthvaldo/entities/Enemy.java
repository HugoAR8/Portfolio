package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;


public class Enemy extends Entity{
	
	public int life = 3;
	

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}
	
	public void tick() {
		y+=speed;
		if(y >= Game.HEIGHT) {
			Game.life-=Entity.rand.nextInt(10)+1;
			Game.entities.remove(this);
			
			return;
		}
		
		for(int i = 0; i < Game.entities.size(); i ++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				if(Entity.isColliding(this, e)) {
					Game.entities.remove(e);
					life--;
					if(life <= 0 ) {
						Explosion explosion = new Explosion(x,y,16,16,0,null);
						Game.entities.add(explosion);
						Game.score++;
						Game.entities.remove(this);
						return;
					}
					break;
				}
			}
			
		}
	}
	

}
