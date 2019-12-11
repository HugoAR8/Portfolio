package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.main.Sound;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.World;

public class Player extends Entity {
	
	public boolean right,up,left,down;
	public int right_dir = 0,left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.8;
	
	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	private BufferedImage playerDamage;
	
	private boolean hasGun = false; 
	
	public int ammo = 0; 
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public boolean shoot = false,mouseShoot = false;
	
	public double life = 100, maxLife = 100;
	public int mx,my;
	
	public boolean jump = false;
	public boolean isJumping = false;
	public int jumpSpeed = 2;
	public boolean jumpUp = false,jumpDown = false;
	
	public int z = 0;
	
	public int jumpFrames = 25,jumpCur = 0;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		playerDamage =  Game.spritesheet.getSprite(0,16,16,16);
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16),16,16,16);
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16),48,16,16);
		}
		for(int i = 0; i < 4; i++) {
			downPlayer[i] = Game.spritesheet.getSprite(32 + (i*16),0,16,16);
		}
		for(int i = 0; i < 4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(32 + (i*16),32,  16,16);
		}
	}

	public void tick() {
		depth = 1;
		
		if(jump) {
			Sound.jump.play();
			if(isJumping == false) {
				jump = false;
				isJumping = true;
				jumpUp = true;
			}
		}
		
		if(isJumping) {
			
				if(jumpUp) {
					jumpCur+=jumpSpeed;
				}else if(jumpDown) {
					jumpCur-=jumpSpeed;
					if(jumpCur <= 0) {
						isJumping = false;
						jumpDown = false;
						jumpUp = false;
					}
				}
				
				z = jumpCur;
				if(jumpFrames <= jumpCur) {
					jumpUp = false;
					jumpDown = true;
				}
			}
		
		moved = false;
		if(ammo <= 0) {
			hasGun = false;
		}else if(ammo >= 1) {
			hasGun = true;
		}
		if(right && World.isFree((int)(x+speed),this.getY(),z)) {
			moved = true;
			dir=right_dir;
			x+=speed;
		}else if(left && World.isFree((int)(x-speed),this.getY(),z)) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed),z)) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}else if(down && World.isFree(this.getX(),(int)(y+speed),z)) {
			moved = true;
			dir = down_dir;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0;
			}
		}
	
			checkCollisionLifePack();
			checkCollisionAmmo();
			checkCollisionGun();
			
			if(isDamaged) {
				this.damageFrames ++;
				if(this.damageFrames == 8) {
					this.damageFrames = 0;
					isDamaged = false;
				}
			}
			
			if(shoot) {
				
				shoot = false;
				if(hasGun && ammo > 0) {
					ammo--;
					//Criar bala e atirar
					int dx = 0;
					int dy = 0;
					int px = 0;
					int py = 0;
					shoot = false;
					if(dir == right_dir) {
						px = 0;
						dx = 1;
					}else if(dir == left_dir) {
						px = -0;
						dx = -1;
					}
					if(dir == up_dir) {
						dy = -1;
					}else if(dir == down_dir) {
						dy = 1;
					}
				
					BulletShoot bullet = new BulletShoot(this.getX() + px, this.getY() + py,3,3,GUN_RIGHT,dx,dy);
					Game.bullets.add(bullet); 
				}
			}
			
			if(mouseShoot) {
				Sound.atack.play();
				
				mouseShoot = false;
				if(hasGun && ammo > 0) {
					ammo--;	
					//Criar bala e atirar
					int px = 0, py = 0;
					double angle = 0;

					if(dir == right_dir) {
						angle = Math.atan2(my - (this.getY()+8 - Camera.y),mx - (this.getX()+8 - Camera.x));
						px = 0;
						
					}else if(dir == left_dir) {
						angle = Math.atan2(my - (this.getY()+8 - Camera.y),mx - (this.getX()+8 - Camera.x));
						px = -0;
				
					}
					if(dir == up_dir) {
						angle = Math.atan2(my - (this.getY()+8 - Camera.y),mx - (this.getX()+8 - Camera.x));
					
					}else if(dir == down_dir) {
						angle = Math.atan2(my - (this.getY()+8 - Camera.y),mx - (this.getX()+8 - Camera.x));
					
					}
					
					double dx = Math.cos(angle);
					double dy = Math.sin(angle);
					
					
					
					
					
					
					BulletShoot bullet = new BulletShoot(this.getX() + px, this.getY() + py,3,3,GUN_RIGHT,dx,dy);
					Game.bullets.add(bullet); 
				}
			}
			
			if(life <=0) {
				// Game Over
				life = 0 ;
				Game.gameState = "GAME_OVER";
			}
			
			
			updateCamera();
			
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void checkCollisionGun() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon ) {
				if(Entity.isColliding(this, atual)) {
					hasGun = true;
					ammo +=1;
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	public void checkCollisionAmmo() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bullet) {
				if(Entity.isColliding(this, atual)) {
					ammo += 10;
					Game.entities.remove(atual);
				}
			}
		}
	}
		
	public void checkCollisionLifePack() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
				if(Entity.isColliding(this, atual)) {
					life+=10;
					if(life > maxLife)
						life = maxLife;
					Game.entities.remove(atual);
				}
			}
		}
			
	}
	
	
			
		
	

	public void render(Graphics g) {
		if(!isDamaged) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y-z,null);
			if(hasGun) {
				//Desenhar arma para direita
				g.drawImage(Entity.GUN_RIGHT,this.getX() +4 - Camera.x,this.getY() - Camera.y-z,null);
			}
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y-z,null);
			if(hasGun) {
				//Desenhar arma para esquerda
				g.drawImage(Entity.GUN_LEFT, this.getX() - 4 - Camera.x, this.getY() - Camera.y-z, null);
			}
		}else if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y-z,null);
			if(hasGun) {
				//Desenhar arma para cima
				g.drawImage(Entity.GUN_UP, this.getX() +6 - Camera.x, this.getY() + 2 - Camera.y-z, null);
			}
		}else if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y-z,null);
			if(hasGun) {
				//Desenhar arma para baixo
				g.drawImage(Entity.GUN_DOWN, this.getX() +2 - Camera.x, this.getY() +5 - Camera.y-z, null);
			}
		}
		}else {
			g.drawImage(playerDamage,this.getX() - Camera.x, this.getY() - Camera.y -z , null);
			if(hasGun) {
				if(dir == right_dir) {
					g.drawImage(Entity.GUN_RIGHT,this.getX() +4 - Camera.x,this.getY() - Camera.y-z,null);
				}else if(dir == left_dir) {
					g.drawImage(Entity.GUN_LEFT, this.getX() - 4 - Camera.x, this.getY() - Camera.y-z, null);
				}else if(dir == down_dir) {
					g.drawImage(Entity.GUN_DOWN, this.getX() +2 - Camera.x, this.getY() +5 - Camera.y-z, null);
				}else if(dir == up_dir) {
					g.drawImage(Entity.GUN_UP, this.getX() +6 - Camera.x, this.getY() + 2 - Camera.y-z, null);
				}
				
			}
		}
		if(isJumping) {
			g.setColor(Color.black);
			g.fillOval(this.getX() - Camera.x +4, this.getY() - Camera.y +12, 8,8	);
			
		}
	}
}
