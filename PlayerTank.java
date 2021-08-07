import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerTank extends Tank implements KeyListener {
	ArrayList<Bullet> bullet=null;
	
	public PlayerTank(int x, int y,Image img) {
		super(x, y,img);
		this.x=x;
		this.y=y;
		this.img=img;
		bullet=new ArrayList<Bullet>();
		bullet.add(new Bullet(0,0,0,img));
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
		cd++;
    }
	public void keyPressed(KeyEvent e) {  
    	int key = e.getKeyCode();
    	switch(key) {
    	case KeyEvent.VK_UP:
    		setDirection(0);
    		break;
    	case KeyEvent.VK_RIGHT:
    		setDirection(1);
    		break;
    	case KeyEvent.VK_DOWN:
    		setDirection(2);
    		break;
    	case KeyEvent.VK_LEFT:
    		setDirection(3);
    		break;
    	case KeyEvent.VK_SPACE:
    		if(this.fire()!=null) {
    		bullet.add(this.fire());
    		cd=0;
    		}
    		break;
    	}
    	
    }
	
}
