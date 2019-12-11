package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.World;

public class Coin extends Entity{
	
	public static boolean isCollecting = false;
	public static int collectionFrame = 0;
	public static int collectionMaxFrame = 32;

	public Coin(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(World.isFree(this.getX(),this.getY() +2 ) ) {
			y += 1;
		}	
	}

}
