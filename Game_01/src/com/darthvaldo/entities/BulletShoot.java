package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;
import com.darthvaldo.world.WallTile;
import com.darthvaldo.world.World;

public class BulletShoot extends Entity {
	
	private double dx;
	private double dy;
	private double spd = 3;
	
	private int life = 100, curLife = 0 ;
	
	public static Entity walltile; 
	
	private int frames = 0, maxFrames = 20, index = 0 , maxIndex = 1;	
	
	private int maskx = 8, masky = 8, maskw = 7, maskh = 9;
	
	private BufferedImage sprites;
	
	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite ,double dx, double dy) {
		super(x,y,width,height,sprite);
		
		this.dx = dx;
		this.dy = dy;
		
		sprites = Game.spritesheet.getSprite(128,0,16,16);
		
		
	}
	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curLife++;
		if(curLife >= life) {
			Game.bullets.remove(this);

			return;
		}
		if(!World.isFree(this.getX(), this.getY(),0)) {
			Game.bullets.remove(this);
			World.generateParticles(100,(int)x,(int)y);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites, this.getX() - Camera.x, this.getY() - Camera.y, null);

	}
	

	}
	


