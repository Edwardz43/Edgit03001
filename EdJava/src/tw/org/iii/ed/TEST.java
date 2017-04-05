package tw.org.iii.ed;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TEST{
	// 下面兩個常量設置縮小後圖片的大小
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	// 定義個BuffedImage對象，用於保存縮小後的位圖
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
	BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	private JFrame frame = new JFrame("顯示圖片");
	MyCanvas canvas = new MyCanvas();
	
	public void zoom() throws Exception {
		// 讀取原始位圖
		Image srcImage = ImageIO.read(new File("C:/Ed/images/001.jpg"));
		// 將原始位圖縮小後繪制到image圖象中
		g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
		// 將image圖象文件輸出到磁盤文件中。
		ImageIO.write(image, "jpeg", new File(System.currentTimeMillis()
				+ ".jpg"));
		canvas.setPreferredSize(new Dimension(300,300));
		//canvas.repaint();
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	class MyCanvas extends Canvas{
		@Override
		public void paint(Graphics g) {
			g.drawImage(image,0,0,null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new TEST().zoom();
	}
}