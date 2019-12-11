package com.darthvaldo.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile{

	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
	public void render(Graphics g) {
		if(World.CICLO == World.dia) {
			g.drawImage(Tile.TILE_DAY, x - Camera.x, y - Camera.y,null);
		}else if(World.CICLO == World.noite) {
			g.drawImage(Tile.TILE_NIGHT, x - Camera.x, y - Camera.y,null);
		}
		
	}

}
