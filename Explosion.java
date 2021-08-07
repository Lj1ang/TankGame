import java.awt.Graphics;
import java.awt.Image;

public class Explosion {
	private int imgX;
	private int imgY;
	private Image img;
	private int frameCounter;//up to 4
	
	public Explosion(int x,int y,Image img) {
		this.imgX=x;
		this.imgY=y;
		this.img=img;
	}
	public void draw(Graphics g) {
		
		g.drawImage(img, imgX, imgY, imgX+34, imgY+34,
				20*34+frameCounter*34,4*34, 21*34+frameCounter*34, 5*34, null);
		frameCounter++;
		
	}
	public boolean isOver() {
		if(frameCounter<=3)
			return false;
		else
			return true;
	}
}
