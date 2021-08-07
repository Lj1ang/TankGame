import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MyFrame extends JFrame{
	private final int GWIDTH=1920;//窗体参数
	private final int GHEIGHT=1080;
	
	private Reborn reborn;
	
	private PlayerTank tank;
	private Image offScreenImage = null;      //声明一个新的Image对象，即第二缓存
    private Graphics gOffScreen = null;
    
     ArrayList<SpiritTank> sTanks=new ArrayList<SpiritTank>();
     
    private int rd=0;
    private Image img=null; 
    private int[][] tankPt= {{100,100},{350,100},{600,100}};
    private int createTime;
    
    private Explosion exp;//精灵坦克爆炸
    private Explosion expt;//玩家坦克爆炸
    
    
    
    private ArrayList<Point> waters;
    
    
    
	public MyFrame(String string) throws IOException {
		// TODO Auto-generated constructor stub
		super("坦克大战");	
		img=Tankimg.getImg();
		SpiritTank atank;
		setSize(GWIDTH, GHEIGHT);//设置窗体的宽和高
		tank= new PlayerTank(GWIDTH/2, GHEIGHT-500,img);
		tank.setCategory(2);
		this.addKeyListener(tank);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setVisible(true);//设置窗体可见
		Thread tankThread;
		tankThread = new Thread(new myThread());	
		tankThread.start();
		
		waters=new ArrayList<Point>();
		this.loadData("map1.txt");
		
	}
	private class myThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Random random = new Random();
			SpiritTank atank;
			int pt;
			while(true) {
				try {					
					Thread.sleep(20);
					createTime++;
					if(createTime>=10) {
						if(random.nextInt(2)==1) {
							pt=random.nextInt(3);
							atank=new SpiritTank(tankPt[pt][0],tankPt[pt][1],img);
							atank.setCategory(4);
							atank.setDirection(2);
							sTanks.add(atank);								
						}
						createTime=0;
					}
					tank.move();
					repaint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	public void paint(Graphics g) {
		//第一次使用offScreenImage时创建它，即第二缓存，后续的绘制内容全部绘制在第二缓存中
        if(offScreenImage == null) {  
        	//截取窗体所在位置的图片
            offScreenImage = this.createImage(GWIDTH, GHEIGHT); 
            //获得截取图片的画布
            gOffScreen = offScreenImage.getGraphics();  
        } 
        super.paint(gOffScreen);//位置
        //清除屏幕        
        Color c = gOffScreen.getColor();  
        gOffScreen.setColor(Color.BLACK); 
        gOffScreen.fillRect(0, 0, GWIDTH, GHEIGHT);
        gOffScreen.setColor(c);
        gOffScreen.setColor(Color.white);
        
        
        
        
        
        for(int i=0;i<3;i++) {
        	gOffScreen.drawImage(img,tankPt[i][0] , tankPt[i][1],tankPt[i][0]+34,
        			 tankPt[i][1]+34,34*4,34*7,34*5,34*8,null);	
        }
        for(Iterator<Point> i = waters.listIterator();i.hasNext();) {
        	Point wp=i.next();
        	Water.draw(gOffScreen, wp.x, wp.y);
        }
        for (Iterator<SpiritTank>  i = sTanks.listIterator() ; 
        		 i.hasNext(); ) {
        	SpiritTank spTank=i.next();
        	spTank.move();
        	spTank.draw(gOffScreen);
        	if(spTank.sBullet!=null &&
        			spTank.sBullet.size()>1) {
        		for(Iterator<Bullet> k =spTank.sBullet.listIterator();
        			k.hasNext();) {
        			Bullet sBu=k.next();
        			if(sBu!=null) {
        				sBu.move();
        				sBu.draw(gOffScreen,spTank);
        				if(tank.isCollide(sBu)) {
        					expt=new Explosion(tank.getX(),
        							tank.getY(),img);
        					tank.setX(10000);
        					tank.setY(10000);
        					expt.draw(gOffScreen);
        					//重生
        					reborn=new Reborn(GWIDTH/2, GHEIGHT-500,img);
        					break;
        				}
        			}
        		}
        	}
        } 
        tank.draw(gOffScreen);
		//bullet 死循环
        //sptank爆炸
		if (tank.bullet!=null &&tank.bullet.size()>1) {
			for(int i=1;i<tank.bullet.size();i++) {
			if(tank.bullet!=null) {
				tank.bullet.get(i).draw(gOffScreen,tank);
				tank.bullet.get(i).move();
			for(int j=sTanks.size()-1;j>=0;j--) {
				SpiritTank spTank=(SpiritTank) sTanks.get(j);
				if(spTank.isCollide(tank.bullet.get(i))) {
					exp=new Explosion(spTank.getX(),
							spTank.getY(),img);
					exp.draw(gOffScreen);
					sTanks.remove(j);
					tank.bullet.remove(i);
					break;
					}
				}
			}
		}
	}
		if(reborn!=null) { 
			reborn.draw(gOffScreen);
			//GWIDTH/2, GHEIGHT-500
			if(reborn.isOver()) {
				tank.setX(GWIDTH/2);
				tank.setY(GHEIGHT-500);
				reborn=null;
			}
		}
		//将第二绘存中的内容一次性全部绘制到屏幕
        g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void loadData(String level) throws IOException{
		 BufferedReader input =
				 new BufferedReader(new FileReader(level));
		 String line = input.readLine();
		 while (line!=null) {
			 StringTokenizer tnzr=new StringTokenizer(line," ");
			 String name = tnzr.nextToken();
			  int x , y ;
			  x = Integer.parseInt(tnzr.nextToken());
			  y = Integer.parseInt(tnzr.nextToken());  
			  Point pt = new Point(x,y);
			  if(name.equals("water")) {
				  waters.add(pt);
			  }
			  System.out.println(waters.size());
			  line=input.readLine();
		 }
		 
	}
	
	
}
