package com.darthvaldo.world;

import com.darthvaldo.entities.Entity;
import com.darthvaldo.entities.Tube;
import com.darthvaldo.main.Game;

public class TubeGenerator {
	
	public int time = 0;
	public int targetTime = 60*2;
	
	
	public void tick() {
		time++;
		if(time == targetTime) {
			//Aqui criamos nosso tubo novo e resetamos o contador.
			int altura1 = Entity.rand.nextInt(50-30) + 30;
			Tube tube1 = new Tube(Game.WIDTH,0,20,altura1,1,Game.spritesheet.getSprite(16,16,16,16));
			
			int altura2 = Entity.rand.nextInt(50-30) + 30;
			Tube tube2 = new Tube(Game.WIDTH,Game.HEIGHT - altura2,20,altura2,1,Game.spritesheet.getSprite(0,16,16,16));
			
			Game.entities.add(tube2);
			Game.entities.add(tube1);
			time = 0;

		}
	}

}
