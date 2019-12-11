package com.darthvaldo.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.darthvaldo.entities.Player;
import com.darthvaldo.main.Game;


public class UI {
	
	public static BufferedImage HEART = Game.spritesheet.getSprite(0,16,8,8);

	 
	public void render(Graphics g) {
		for(int i = 0; i < (int)(Game.life); i++) {
			g.drawImage(HEART,10+ (i*30),10,25,25,null);
		}
		
		g.setFont(new Font("arial", Font.BOLD,19));
		g.setColor(Color.white);
		g.drawString("Gold :"+Game.gold,Game.WIDTH * Game.SCALE - 120, 25);
		
	}
	
}
