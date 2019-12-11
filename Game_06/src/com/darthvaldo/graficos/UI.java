package com.darthvaldo.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.darthvaldo.entities.Player;
import com.darthvaldo.main.Game;
import com.darthvaldo.world.World;


public class UI {
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	public static int hours = 0;
	
	public void tick() {
		frames++;
		if(frames == 60) {
			frames = 0;
			seconds++;
		}
		if(seconds == 60) {
			seconds = 0;
			minutes++;
			if(UI.minutes % 1 == 0) {
				World.CICLO++;
				if(World.CICLO > World.noite) {
					World.CICLO = 0;
				}
			}
		}
		if(minutes == 60) {
			minutes = 0;
			hours++;
		}
	}

	 
	public void render(Graphics g) {
		int curLife = (int)((Game.player.life/Game.player.maxLife) * 200);
		int maxLife = Game.player.maxLife * 2;
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH*Game.SCALE - 715,10,maxLife,20);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH*Game.SCALE - 715,10,curLife,20);
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString((int)(Game.player.life)+"/" + Game.player.maxLife,Game.WIDTH*Game.SCALE-645,27);
		
		String formatTime = "";
		if(hours < 10) {
			formatTime += "0"+hours+":";
		}else {
			formatTime +=hours+":";
		}
		if(minutes < 10) {
			formatTime += "0"+minutes+":";
		}else {
			formatTime += minutes+":";
		}
		if(seconds < 10) {
			formatTime += "0" + seconds;
		}
		else {
			formatTime += seconds;
		}
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString(formatTime,300,30);
	}
	
}
