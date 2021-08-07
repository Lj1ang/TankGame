import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {
	private int x;
	private int y;
	private int v=2;
	private int d;
	private Image img;
	
	public Bullet(int x, int y,int dir,Image img) {
		this.x=x;
		this.y=y;
		this.d=dir;
		this.img=img;
	}
	public void move() {
				switch(d) {
				case 0:  //up
					y -=10;
					break;
				case 1:  //right
					x += 10;
					break;
				case 2:  //down
					y += 10;
					break;
				case 3:  //left
					x -= 10;
					break;	
		    	}	    
		}
	public void draw(Graphics g ,Tank e) {
		if(e instanceof SpiritTank)
			g.drawImage(img,x,y,x+34,y+34,34*5,34*6,34*6,34*7,null);
		if(e instanceof PlayerTank) {
			if(d==0) //up
				g.drawImage(img,x+14,y,x+20,y+8,1,171,7,178,null);
			
			if(d==1)//right£¨x”““∆34
				g.drawImage(img,x-8+34,y+14,x+34,y+20,11,181,19,187,null);
			if(d==2)//down£¨yœ¬“∆34
				g.drawImage(img,x+14, y+34, x+20, y+8+34,11,171,17,179, null);
			if(d==3)//left£¨y◊Û“∆34
				g.drawImage(img,x+34-34,y+14,x+42-34,y+20,1,181,9,187,null);
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
