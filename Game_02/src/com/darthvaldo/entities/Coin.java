package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

public class Coin extends Entity{

	public Coin(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		depth = 0;
	}

}
