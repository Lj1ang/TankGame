import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tank extends KeyAdapter{
	protected int x,y;
	protected int category = 0;
	protected int direction=0;//0 up  1-right 2-down 3-left
	private int flag=0;
	protected Image img;
	private int imgX,imgY;
	protected int cd;
	
	public Tank(int x,int y,Image img) {
		this.x = x;
		this.y = y;
		this.img=img;
	}
	
	public void calculate() {
		imgX = category*68*4+direction*68;
		imgY = category/4*34;
		if(imgY == 34) {
			imgX = imgX - 68*16;
		}
		
	}
	public void setCategory(int category) {
		this.category = category;
		this.calculate();
		
		
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
		this.calculate();
	}
	
	public void draw(Graphics g) {				
		flag = (flag+1)%2;
		if(flag == 0) {
			g.drawImage(img,x,y,x+34,y+34,imgX,imgY,imgX+34,imgY+34,null);
		}else {		
			g.drawImage(img,x,y,x+34,y+34,imgX+34,imgY,imgX+68,imgY+34,null);
		}	
	}
	public  Bullet fire() {
		Bullet bullet1=null;
		int bullet1X=x;
		int bullet1Y=y;
		if(cd>=10) {
			return new Bullet(bullet1X,bullet1Y,direction,img);	
			}
		else
		{
			return null;
		}
	}
	//Êý¾ÝÐÞÕý
	public boolean isCollide(Bullet bullet) {
		if(bullet.getX()+17>x&&
			bullet.getX()<x+17&&
			bullet.getY()+17>y&&
			bullet.getY()<y+17) {
			return true;
		}
		return false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
		
	}
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
