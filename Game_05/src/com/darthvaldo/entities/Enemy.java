package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.AStar;
import com.darthvaldo.world.Vector2i;
import com.darthvaldo.world.World;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	
	public double life = 16;
	
	public int gravity = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		path = AStar.findPath(Game.world,new Vector2i(World.xINICIAL,World.yINICIAL),new Vector2i(World.xFINAL,World.yFINAL));
	}
	
	public void tick() {
		
		followPath(path);
		if(x >= Game.WIDTH) {
			//perder vida 
			Game.life -= Entity.rand.nextDouble();
			Game.entities.remove(this);
			return;
		}
		if(life <= 0) 
		{
			Game.gold += 10;
			World.generateParticles(300, this.getX(),this.getY());
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.red);
		g.fillRect((int)x,(int)(y+20),16,3);
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int)(y+20),(int)((life/16)*16),3);
	}
}
