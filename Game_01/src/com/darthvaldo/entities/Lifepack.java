package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

public class Lifepack extends Entity{

	public Lifepack(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		depth = 0;
	}

}
