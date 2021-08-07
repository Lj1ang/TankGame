import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tankimg {
	private static Image img=null;
	//无法访问constructor------private
	private Tankimg() {
		
	}
	public static Image getImg() {
		if(img==null) {
			File f = new File("robots_sprite.png");//建立文件对象
			try {//文件操作放在错误处理块中
				img = ImageIO.read(f);//从文件中读取图像
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

}

