package com.darthvaldo.entities;

import java.awt.image.BufferedImage;

import com.darthvaldo.main.Game;
import com.darthvaldo.world.World;

public class TowerController extends Entity{
	
	public boolean isPressed = false;
	public int xTarget,yTarget; 

	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(isPressed) {
			//Criar torre aqui
			isPressed = false;
			boolean liberado = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			Player player = new Player(xx,yy,16,16,0,Game.spritesheet.getSprite(16, 16, 16,16));
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(Entity.isColliding(e,player)) {
						liberado = false;
					}
				}
			}
			
			
			if(World.isFree(xx,yy)) {
				liberado = false;
				
			}
			
			if(liberado) {
				if(Game.gold >= 25) {
					Game.entities.add(player);
					Game.gold -= 25;
				}else {
					//Mensagem de out of money na tela.
					System.out.println("Pobre");
				}
			}
			
		}
		if(Game.life <= 0) {
			//Game over
			System.exit(1);
		}
		
	}

}
