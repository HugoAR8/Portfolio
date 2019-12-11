package com.darthvaldo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;

public class Spawner extends Entity{
	
	private int timer = 60;
	private int curTimer = 0;
	

	public Spawner(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		//Criar inimigos.
		curTimer++;
		if(curTimer == timer) {
			//Gerar inimigo!
			curTimer = 0;
			timer = Entity.rand.nextInt(60 - 30) + 30;
			Enemy enemy = new Enemy(x,y,16,16,Entity.rand.nextDouble()+Entity.rand.nextInt(2) ,Entity.ENEMY_SPRITE_RIGHT);
			Game.entities.add(enemy);
		}
	}
	
	public void render(Graphics g ) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int)y,width,height);
	}

}
