import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class SpiritTank extends Tank {
	private int rd=0;
	protected ArrayList<Bullet> sBullet=null;
	
	private Random random = new Random();
	
	public SpiritTank(int x,int y,Image img) {
		super(x, y,img);
		this.x=x;
		this.y=y;
		sBullet=new ArrayList<Bullet>();
		sBullet.add(new Bullet(0,0,0,img));
	}
	
	public void move() {
		switch(direction) {
		case 0:  //up
			y = y-3;
			break;
		case 1:  //right
			x += 3;
			break;
		case 2:  //down
			y += 3;
			break;
		case 3:  //left
			x -= 3;
			break;
		}		
		rd++;
		cd++;
		rd = rd %20;
		
		//
		if(this.fire()!=null) {
			if(random.nextInt(50)==25) {
			sBullet.add(this.fire());
			cd=0;
		}
	}	
		if(rd == 0) {			
			if(random.nextInt(2)==1) {
				super.setDirection(random.nextInt(4));
			}	       
		}
		
	}
}

