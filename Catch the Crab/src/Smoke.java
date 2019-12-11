import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Smoke {
	
	public int x,y;
	
	public static BufferedImage[] smokeSprite;
	
	public int curFrames = 0,maxFrames = 4	, maxAnim = 3, curAnim = 0;
	
	public Smoke(int x, int y) {
		this.x = x;
		this.y = y;
		smokeSprite = new BufferedImage[3];
		smokeSprite[0] = Game.spritesheet.getSprite(16*2,0);
		smokeSprite[1] = Game.spritesheet.getSprite(16*3,0);
		smokeSprite[2] = Game.spritesheet.getSprite(16*4,0);
		
	}
	
	public void update() {
		
		curFrames++;
		if(curFrames == maxFrames) {
			curFrames = 0;
			curAnim++;
			if(curAnim == maxAnim) {
				curAnim = 0;
				Game.smokes.remove(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(smokeSprite[curAnim],(int)x,(int)y,48,48,null);
	}

}
