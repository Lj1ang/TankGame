import java.awt.Graphics;
import java.awt.Image;

public class Reborn {
	private int x;
	private int y;
	private int frameNumber=0;
	private int imgx;
	private int imgy;
	private Image img;
	
	public Reborn(int x,int y,Image img) {
		this.x=x;
		this.y=y;
		imgx=13*34;
		imgy=7*34;
		this.img=img;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, x+34, y+34,
				imgx+34*(frameNumber%3),imgy,imgx+34+34*(frameNumber%3),imgy+34,null);
		frameNumber++;		
	}
	public boolean isOver() {
		if(frameNumber>=30) {
			return true;
			}
		else
			return false;
	}
}
