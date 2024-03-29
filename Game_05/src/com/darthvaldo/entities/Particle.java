package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.Camera;

public class Particle extends Entity{
	
	public Particle(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
	}

	public int lifeTime = 10;
	public int curLife = 0;
	
	public int spd = 2;
	public double dx = 0;
	public double dy = 0;
	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curLife ++;
		if(lifeTime == curLife) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y,width,height);
	}


}
