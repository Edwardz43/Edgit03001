package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.AbstractDocument.Content;

public class MyACD extends JFrame{
	int WIDTH=1024;
	int HEIGHT=768;
	BufferedImage image;
	Graphics g ;
	MyCanvas canvas;
	JButton open, exit, up, down;
	File file;
	ArrayList<String> imagePath;
	JScrollPane js;
	
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
		
		//addKeyListener(new myKeyAdapter());
		
		//視窗大小監聽  好讓後面的canvas 跟圖像可以隨視窗調整大小
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e)
		      {
		        final Component c = e.getComponent();
		        canvas.setPreferredSize(new Dimension((int) (c.getWidth()), c.getHeight()));
		        canvas.revalidate();
		      }
		});
		
		JPanel bottom = new JPanel();
		canvas = new MyCanvas();
		imagePath = new ArrayList<>();
		bottom.add(open);bottom.add(up);bottom.add(down);bottom.add(exit);
		add(bottom, BorderLayout.SOUTH);
		js = new JScrollPane(canvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(js, FlowLayout.CENTER);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	protected void downPage() {
		try{
			imagePath.indexOf(file.getAbsolutePath());
			String dPage = imagePath.get(imagePath.indexOf(file.getAbsolutePath())+1);
			file = new File(dPage.toString());
			Image srcImage = ImageIO.read(file);
			int w =srcImage.getWidth(null) ; int h = srcImage.getHeight(null);
			canvas.setPreferredSize(new Dimension(w,h));
			image = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
			g = image.getGraphics();
			g.drawImage(srcImage, 0, 0, w, h, null);
			//pack();
			js.setPreferredSize(new Dimension(w,h));
			canvas.setPreferredSize(new Dimension(w,h));
			canvas.repaint();
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}
		
	}

	protected void upPage() throws IOException {
		try{
			imagePath.indexOf(file.getAbsolutePath());
			String uPage = imagePath.get(imagePath.indexOf(file.getAbsolutePath())-1);
			file = new File(uPage.toString());
			Image srcImage = ImageIO.read(file);
			int w =srcImage.getWidth(null) ; int h = srcImage.getHeight(null);
			image = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
			g = image.getGraphics();
			g.drawImage(srcImage, 0, 0, w, h, null);
			js.setPreferredSize(new Dimension(w,h));
			canvas.setPreferredSize(new Dimension(w,h));
			//pack();
			canvas.repaint();
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}
	}
	//開啟檔案
	public void open() throws Exception {
		JFileChooser fc = new JFileChooser();
		int option = fc.showDialog(null, null);
		if(option == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
			Image srcImage = ImageIO.read(file);
			
			//抓一下原圖檔的大小
			int w =srcImage.getWidth(null) ; int h = srcImage.getHeight(null);
			image = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
			g = image.getGraphics();
			g.drawImage(srcImage, 0, 0, w, h, null);
			
			//配合視窗的縮放來調整canvas的大小
			js.setPreferredSize(new Dimension(w,h));
			canvas.setPreferredSize(new Dimension(w,h));
			pack();
			
			//畫出圖像
			canvas.repaint();
		}
		File parentFile = new File(file.getParent());
		for(String s:parentFile.list()){
			imagePath.add(parentFile.getAbsolutePath()+"\\"+s);
		}
	}
//	class myKeyAdapter extends KeyAdapter{
//		@Override
//		public void keyReleased(KeyEvent e){
//			if(e.getKeyCode()==37){
//				try {
//					upPage();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}else if(e.getKeyCode()==39){
//				downPage();
//			}
//		}
//	}
	//override一下paint的畫法
	class MyCanvas extends Canvas{
		@Override
		public void paint(Graphics g) {
			//根據canvas的大小來決定圖象的縮放
			g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new MyACD();
	}
}
