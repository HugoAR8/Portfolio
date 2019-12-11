package com.darthvaldo.main;

import com.darthvaldo.entities.Enemy;
import com.darthvaldo.entities.Entity;
import com.darthvaldo.world.World;
public class EnemySpawn {

	public int interval = 60*2;
	public int curTime = 0;
	
	public void tick() {
		if(World.CICLO == World.noite) {
			curTime++;
			if(curTime == interval) {
				curTime = 0;
				int xInitial = Entity.rand.nextInt(World.WIDTH*16 -16 - 16)+16;
				Enemy enemy = new Enemy(xInitial,16,16,16,1,Entity.ENEMY1_RIGHT,Entity.ENEMY1_LEFT);
				Game.entities.add(enemy);
			}
		}
		
	}
}
