package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.World;

public class Player extends Entity {
	
	public boolean right,left; 
	
	public double life = 100;
	public int maxLife = 100;
	
	public int dir = 1;
	public double gravity = 0.3;
	public double vspd = 0;
	
	public boolean jump = false;
	
	private int framesAnimationIdle = 0;
	private int maxFrames = 30;
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public BufferedImage ATACK_RIGHT;
	public BufferedImage ATACK_LEFT;
	
	public boolean atack = false;
	public boolean isAtacking = false;
	public int atackFrames = 0;
	public int atackmaxFrames = 5;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		
		ATACK_RIGHT = Game.spritesheet.getSprite(2*16,4*16,16,16);
		ATACK_LEFT = Game.spritesheet.getSprite(3*16,4*16,16,16);
	}

	public void tick() {
		depth = 2;
		vspd+=gravity;
		
		if(!World.isFree((int)x,(int)(y+1)) && jump) {
			vspd = -5;
			jump = false;
		}
		if(!World.isFree((int)x,(int)(y+vspd))) {
			int signVsp = 0;
			if(vspd >= 0) {
				signVsp = 1;
			}else {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;
			}
			vspd=0;
		}
		
		y = y+vspd;

		if(right && World.isFree((int)(x+speed),(int)y )) {
			x+=speed;
			dir = 1;
		}else if(left&& World.isFree((int)(x-speed),(int)y )) {
			x-=speed;
			dir = -1;
		}
		
		//Sistema de ataque
		if(atack) {
			if(isAtacking == false) {
				atack = false;
				isAtacking = true;
			}
		}
		if(isAtacking) {
			atackFrames++;
			if(atackFrames == atackmaxFrames) {
				atackFrames = 0;
				isAtacking = false;
			}
		}
		
		collisionEnemy();
				
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2,0,World.WIDTH *16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2,0,World.HEIGHT * 16 - Game.HEIGHT);
	}
	
	public void collisionEnemy() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.rand.nextInt(100) < 30) {
					if(Entity.isColliding(this, e)) {
						life -= 0.3;
						if(isAtacking) {
							((Enemy) e).life-=30;
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		framesAnimationIdle ++;
		if(framesAnimationIdle == maxFrames) {
			curSprite++;
			framesAnimationIdle = 0;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		
		if(dir == 1) {
			sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
			if(isAtacking ) {
				g.drawImage(ATACK_RIGHT,this.getX()+10 - Camera.x,this.getY() - Camera.y,null);
			}
			
		}else if(dir == -1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			if(isAtacking ) {
				g.drawImage(ATACK_LEFT,this.getX()-10 - Camera.x,this.getY() - Camera.y,null);
			}
		}
		super.render(g);
	}


}
