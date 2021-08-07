package mapBlock;
import java.awt.Graphics;

public abstract class Block {
	protected int x;
	protected int y;
	final protected int WIDTH;
	
	public Block(int x,int y) {
		this.x=x;
		this.y=y;
		WIDTH=34;
	}
	abstract public void draw (Graphics g);	
}
