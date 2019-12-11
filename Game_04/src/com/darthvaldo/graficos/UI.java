package com.darthvaldo.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.darthvaldo.entities.Player;
import com.darthvaldo.main.Game;


public class UI {

	 
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(10, 10, 200,20);
		g.setColor(Color.green);
		g.fillRect(10, 10, (int)((Player.life/100) * 200),20);
		g.setColor(Color.white);
		g.drawRect(10,10,200,20);
		g.setFont(new Font("arial", Font.BOLD, 17));
		g.drawString("Coins: "+ Player.currentCoins + "/" + Player.maxCoins,Game.WIDTH + 140 ,20);
		
	}
	
}
