package com.darthvaldo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;

public class Explosion extends Entity{
	
	private int frames = 0;
	private int targetFrames = 3;
	private int maxAnimation = 4;
	private int curAnimation = 0;
	
	public BufferedImage[] explosionSprites = new BufferedImage[5];

	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		explosionSprites[0] = Game.spritesheet.getSprite(0,16,16,16);
		explosionSprites[1] = Game.spritesheet.getSprite(16,16,16,16);
		explosionSprites[2] = Game.spritesheet.getSprite(16*2,16,16,16);
		explosionSprites[3] = Game.spritesheet.getSprite(16*3,16,16,16);
		explosionSprites[4] = Game.spritesheet.getSprite(16*4,16,16,16);
	}
	
	public void tick() {
		frames++;
		if(frames == targetFrames) {
			frames = 0;
			curAnimation ++;
			if(curAnimation > maxAnimation) {
				Game.entities.remove(this);
				return;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(explosionSprites[curAnimation], this.getX(),this.getY(),null);
	}

}
