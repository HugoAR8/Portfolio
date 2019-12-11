package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.World;

public class Player extends Entity {
	
	public boolean right,left;
	
	public static double life = 100;
	
	public static int currentCoins = 0;
	public static int maxCoins = 0;
	
	public int dir = 1;
	public double gravity = 4;
	public double vspd = 0;
	
	public boolean jump = false;
	
	public int jumpForce = 4;
	public boolean isJumping = false;
	public int jumpHeight = 24;
	public int jumpFrames = 0;
	
	private int framesAnimationIdle = 0;
	private int maxFrames = 15;
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
	}

	public void tick() {
		depth = 2;
		/*vspd+=gravity;
		
		if(!World.isFree((int)x,(int)(y+1)-4) && jump) {
			vspd = 6;
			jump = false;
		}
		if(!World.isFree((int)x,(int)(y+vspd) -4)) {
			int signVsp = 0;
			if(vspd >= 0) {
				signVsp = 1;
			}else {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp) - 4)) {
				y = y+signVsp;
			}
			vspd=0;
		}
		
		y = y+vspd;
		*/
		
		if(World.isFree((int)x, (int)(y+gravity) - 4) && isJumping == false) {
			y+=gravity;
			for(int i = 0; i < Game.entities.size(); i ++ ) {
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					if(Entity.isColliding(this, e)) {
						isJumping = true;
						jumpHeight = 48;
						((Enemy) e).life--;
						if(((Enemy) e).life == 0) {
							Game.entities.remove(i);
							break;
						}
					}else {
						jumpHeight = 24;
					}
					}
				}
			}
		
		
		
		if(right && World.isFree((int)(x+speed),(int)y - 4)) {
			x+=speed;
			dir = 1;
		}else if(left&& World.isFree((int)(x-speed),(int)y - 4)) {
			x-=speed;
			dir = -1;
		}
		
		if(jump) {
			if(!World.isFree(this.getX(),this.getY()+1)) {
				isJumping = true;
			}else {
				jump = false;
			}
		}
		if(isJumping) {
			if(World.isFree(this.getX(), (this.getY()-2)-4)) {
				y-=jumpForce;
				jumpFrames+=2;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		//detectar dano
		for(int i = 0; i < Game.entities.size(); i ++ ) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColliding(this, e)) {
					if(Entity.rand.nextInt(100) < 5) {
						life--;
					}
				}
			}
		}
		//detectar colisão com moedas
		for(int i = 0; i < Game.entities.size(); i ++ ) {
			Entity e = Game.entities.get(i);
			if(e instanceof Coin) {
				if(Entity.isColliding(this, e)) {
					Coin.isCollecting = true;
					Game.entities.remove(i);
					Player.currentCoins++;
					
					break;
				}
			}
		}
		
		
			
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2,0,World.WIDTH *16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2,0,World.HEIGHT * 16 - Game.HEIGHT);
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
			
		}else if(dir == -1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
		}
		super.render(g);
	}


}
