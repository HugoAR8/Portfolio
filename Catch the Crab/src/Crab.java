import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crab {

	
	public double x,y,dx,dy;
	public double spd = 4;
	
	public static BufferedImage[] crabSprite;
	
	public int curFrames = 0,maxFrames = 15	, maxAnim = 2, curAnim = 0;
	
	public Crab(int x, int y) {
		this.x = x;
		this.y = y;
		double radius = Math.atan2((Game.HEIGHT/2 - 20) - y,(Game.WIDTH/2 - 20) - x );
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);
		crabSprite = new BufferedImage[2];
		crabSprite[0] = Game.spritesheet.getSprite(0,0);
		crabSprite[1] = Game.spritesheet.getSprite(16,0);
	}
	
	public void update() {
		x+=dx*spd;
		y+=dy*spd;
		if(new Rectangle((int)x,(int)y,48,48).intersects(Game.mascBuraco)) {
			Game.crabs.remove(this);
			return;
		}
		
		curFrames++;
		if(curFrames == maxFrames) {
			curFrames = 0;
			curAnim++;
			if(curAnim == maxAnim) {
				curAnim = 0;
			}
		}
		
		verificaColisao();
		
	}
	
	public void verificaColisao() {
		if(Game.isPressed) {
			if(Game.mx >= x && Game.mx <= x +48) {
				if(Game.my >= y && Game.my <= y + 48) {
					Game.crabs.remove(this);
					Game.score++;
					Game.smokes.add(new Smoke((int)x,(int)y));
					return;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(crabSprite[curAnim],(int)x,(int)y,48,48,null);
		
	}
}
