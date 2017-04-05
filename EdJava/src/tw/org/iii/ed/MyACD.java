package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.FileChooserUI;

public class MyACD extends JFrame{
	// 下面兩個常量設置縮小後圖片的大小
	private final int WIDTH = 1440;
	private final int HEIGHT = 960;
	// 定義個BuffedImage對象，用於保存縮小後的位圖
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
	BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	MyCanvas canvas;
	JButton open, exit, up, down;
	File file;
	
	public MyACD(){
		super("看圖程式");
		setLayout(new BorderLayout());
		open = new JButton("開啟檔案");
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		up = new JButton("上一頁");
		up.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					upPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		down = new JButton("下一頁");
		down.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				downPage();
			}
		});
		
		exit = new JButton("離開");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		addKeyListener(new myKeyAdapter());
		
		
		JPanel bottom = new JPanel();
		canvas = new MyCanvas();
		bottom.add(open);bottom.add(up);bottom.add(down);bottom.add(exit);
		add(bottom, BorderLayout.SOUTH);
		add(canvas, FlowLayout.CENTER);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	protected void downPage() {
		String s = file.toString();
		//System.out.println(s);
		int p = Integer.parseInt(s.substring(s.length()-7, s.length()-4))+1;
		System.out.println(p);
		StringBuffer dpp = new StringBuffer();
		try{
			if(p >= 100){
				dpp = dpp.append(s.substring(0, s.length()-7)+p+".jpg");
			}else if(p<100 && p>=10){
				dpp = dpp.append(s.substring(0, s.length()-7)+"0"+p+".jpg");
			}else{
				dpp = dpp.append(s.substring(0, s.length()-7)+"00"+p+".jpg");
			}
			System.out.println(dpp);
			file = new File(dpp.toString());
			Image srcImage = ImageIO.read(file);
			// 將原始位圖縮小後繪制到image圖象中
			g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
			// 將image圖象文件輸出到磁盤文件中。
			canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			canvas.repaint();
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}
		
	}

	protected void upPage() throws IOException {
		String s = file.toString();
		//System.out.println(s);
		int p = Integer.parseInt(s.substring(s.length()-7, s.length()-4))-1;
		System.out.println(p);
		StringBuffer upp = new StringBuffer();
		try{
			if(p >= 100){
				upp = upp.append(s.substring(0, s.length()-7)+p+".jpg");
			}else if(p<100 && p>=10){
				upp = upp.append(s.substring(0, s.length()-7)+"0"+p+".jpg");
			}else{
				upp = upp.append(s.substring(0, s.length()-7)+"00"+p+".jpg");
			}
			//System.out.println(upp);
			file = new File(upp.toString());
			Image srcImage = ImageIO.read(file);
			// 將原始位圖縮小後繪制到image圖象中
			g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
			// 將image圖象文件輸出到磁盤文件中。
			canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			canvas.repaint();
			
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}
	}

	public void open() throws Exception {
		// 讀取原始位圖
		JFileChooser fc = new JFileChooser();
		int option = fc.showDialog(null, null);
		if(option == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
			Image srcImage = ImageIO.read(file);
			// 將原始位圖縮小後繪制到image圖象中
			g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
			// 將image圖象文件輸出到磁盤文件中。
			canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			canvas.repaint();
			
		}
	}
	class myKeyAdapter extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode()==37){
				try {
					upPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(e.getKeyCode()==39){
				downPage();
			}
		}
	}
	class MyCanvas extends Canvas{
		@Override
		public void paint(Graphics g) {
			g.drawImage(image,0,0,null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new MyACD();
	}
}
