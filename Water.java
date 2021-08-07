import java.awt.Graphics;
import java.awt.Image;

public class Water {
	private int x;
	private int y;

	
	public Water(int x, int y) {
		this.x=x;
		this.y=y;
		
	}
	
	public static void draw(Graphics g ,int x, int y) {
		g.drawImage(Tankimg.getImg(),x,y,x+34,y+34,0,7*34,34,8*34,null);
	}
	
}
