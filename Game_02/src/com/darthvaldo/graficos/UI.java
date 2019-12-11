package com.darthvaldo.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.darthvaldo.entities.Player;
import com.darthvaldo.main.Game;

public class UI {

	 
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,18));
		g.drawString("Maçãs:" + Game.coins_actual+"/"+Game.coins_count, 30, 30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,18));
		g.drawString("Vida > " + Player.life, 30,50);
		
	}
	
}
