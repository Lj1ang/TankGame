import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tankimg {
	private static Image img=null;
	//�޷�����constructor------private
	private Tankimg() {
		
	}
	public static Image getImg() {
		if(img==null) {
			File f = new File("robots_sprite.png");//�����ļ�����
			try {//�ļ��������ڴ��������
				img = ImageIO.read(f);//���ļ��ж�ȡͼ��
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

}

