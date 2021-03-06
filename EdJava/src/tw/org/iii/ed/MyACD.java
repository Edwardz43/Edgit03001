package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
/*
 * 4/6
 * 將課程內容:Jswing、IO 以圖像瀏覽器的形式實作
 * 可以讀取檔案  輸出圖像到視窗程式    以及另存圖檔
 * 並且可切換上下頁  配合視窗縮放 
 * 4/14
 * 新增圖檔另存圖檔功能
 */

public class MyACD extends JFrame{
	//視窗預設大小
	int WIDTH=800;
	int HEIGHT=640;
	BufferedImage bi;
	Graphics2D g2d;
	MyCanvas canvas;//重要的畫布
	JButton open, exit, pre, next, save;
	File readFile, saveFile;
	JPanel bottom;
	ArrayList<String> imagePath;//用來儲存檔案path
	JScrollPane js;//圖像太大的話 設定視窗可以滾動
	String currentFile="";
	String parentName="";
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	//預設開啟圖像的大小
	int screenW = (int)screenSize.getWidth();
	int screenH = (int)(screenSize.getHeight()*0.97);
	int w, h;
	public MyACD(){
		super("圖像瀏覽器");
		setLayout(new BorderLayout());
		
		//依序設定按鈕及功能
		open = new JButton("開啟檔案");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					open();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				bottom.requestFocus();
			}
		});
		
		pre = new JButton("上一頁");
		pre.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					prePage();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bottom.requestFocus();
			}
		});
		
		next = new JButton("下一頁");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextPage();
				bottom.requestFocus();
			}
		});
		
		exit = new JButton("離開");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//視窗大小監聽  好讓後面的canvas跟圖像可以隨視窗調整大小
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e){
		        //偵測視窗的大小
				final Component c = e.getComponent();
		        
				//將canvas調整成和視窗一樣的大小
				canvas.setPreferredSize(new Dimension(c.getWidth(), c.getHeight()));
		        //通知父容器 重新繪製布局
				canvas.revalidate();
		    }
		});
		
		bottom = new JPanel();
		bottom.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ee){
				int envent = ee.getKeyCode();
				switch(envent){
					case KeyEvent.VK_LEFT:
					try {
						prePage();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					case KeyEvent.VK_RIGHT:
						nextPage();
						break;
					case KeyEvent.VK_ESCAPE:
						open();
				}
			}
		});
		
		save = new JButton("另存圖片");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		
		imagePath = new ArrayList<>();
		bottom.add(open);bottom.add(pre);bottom.add(next);bottom.add(exit);bottom.add(save);
		add(bottom, BorderLayout.SOUTH);
		
		//防止圖檔過大  設置一個可滾動區域
		canvas = new MyCanvas();
		js = new JScrollPane(canvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//尚待解決問題: 圖檔過大 需要滾動時  圖檔讀取會出問題
		js.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				//System.out.println(e);
				//canvas.repaint();
				canvas.revalidate();
			}
		});
		
		add(js, FlowLayout.CENTER);
		
		//預設視窗大小
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//focus bottom  這樣就可以用左右鍵控制上下頁
		bottom.requestFocus();
	}
	
	//另存圖片
	protected void saveFile() {
		try{
			JFileChooser fc = new JFileChooser(currentFile);
			int option = fc.showSaveDialog(null);
			if(option == JFileChooser.APPROVE_OPTION){
				saveFile = fc.getSelectedFile();
				//簡單一行  寫入 (目標影像, 格式, 目的地)
				ImageIO.write(bi, "bmp", saveFile);	
			}
		}catch(Exception ee){
				ee.toString();
		}	
	}
	
	//開啟檔案
	public void open() {
		try{
			//用filechooser選擇開啟的檔案  預先設定path這樣方便下次開啟檔案
			JFileChooser fc = new JFileChooser(currentFile);
			int option = fc.showDialog(null, null);
			if(option == JFileChooser.APPROVE_OPTION){
				readFile = fc.getSelectedFile();
				currentFile =readFile.getPath();
				//將檔案轉成影像image
				Image srcImage = ImageIO.read(readFile);
				
				//抓一下原圖檔的大小
				w =(int)(srcImage.getWidth(null)*0.9); h = (int)(srcImage.getHeight(null)*0.9);
				
				//預設一塊空白區域 可以容納image的size  
				bi = new BufferedImage(w, h,
							BufferedImage.TYPE_INT_RGB);
				g2d = (Graphics2D)bi.getGraphics();
				
				//將影像寫入上面的區域
				g2d.drawImage(srcImage, 0, 0, w, h, null);
				pack();
					
				//設定視窗大小 
				setSize(new Dimension(screenW, screenH));
					
				//扣板機  畫出圖像
				canvas.repaint();
			
				/*當開啟第一張圖檔的同時  順便找出上一層的資料夾(parentfile)位置
				 * 然後將同一個資料夾裡的圖檔的path一併存放在ArrayList裡
				 * 之後要跳前後頁  就可以利用List裡的index來找出位置 
				 */
				File parentFile = new File(readFile.getParent());
				parentName = parentFile.getName();
				//foreach 將全部圖檔的path存在ArrayList
				for(String s:parentFile.list()){
					imagePath.add(parentFile.getAbsolutePath()+"\\"+s);
				}
				//左上顯示檔案來源及頁數
				setTitle("圖像瀏覽器-"+parentName+"-"+readFile.getName());
			}
		}catch(Exception ee){
				ee.toString();
		}
	}
		
	//跳下一頁
	protected void nextPage() {
		try{
			//尋找目前頁面的index
			//將index +1,找出下一頁的path 弄成string
			String nPage = imagePath.get(imagePath.indexOf(readFile.getAbsolutePath())+1);
			//利用path找出下一頁的檔案
			readFile = new File(nPage);
			
			//讀取下一頁的檔案  轉成image影像檔 準備畫出
			Image srcImage = ImageIO.read(readFile);
			
			//先抓出原圖的尺寸
			w =(int)(srcImage.getWidth(null)*0.9); h = (int)(srcImage.getHeight(null)*0.9);
			
			//預設一塊區域 可以容納image的size 
			bi = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
			g2d = (Graphics2D)bi.getGraphics();
			
			//將影像寫入上面的區域
			g2d.drawImage(srcImage, 0, 0, w, h,null);
			
			//設定視窗大小 
			setSize(new Dimension(screenW, screenH));
			
			//扣下板機  畫出圖像
			canvas.repaint();
			
			//左上顯示檔案來源及頁數
			setTitle("圖像瀏覽器-"+parentName+"-"+readFile.getName());
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}	
	}
	
	//跳上一頁
	protected void prePage() throws IOException {
		try{
			//尋找目前頁面的index => imagePath.indexOf(readFile.getAbsolutePath())
			//將index -1  弄成string
			String pPage = imagePath.get(imagePath.indexOf(readFile.getAbsolutePath())-1);
			
			//利用path找出上一頁的檔案
			readFile = new File(pPage);
			
			//讀取上一頁的檔案  轉成image影像檔  準備畫出
			Image srcImage = ImageIO.read(readFile);
			
			//抓出原圖的尺寸
			w =(int)(srcImage.getWidth(null)*0.9); h = (int)(srcImage.getHeight(null)*0.9);
			
			//預設一塊空白區域 可以容納image的size 
			bi = new BufferedImage(w, h,
						BufferedImage.TYPE_INT_RGB);
			g2d = (Graphics2D)bi.getGraphics();
			
			//將影像寫入上面的區域
			g2d.drawImage(srcImage, 0, 0, w, h,null);
			
			//設定視窗大小 
			setSize(new Dimension(screenW, screenH));
			//扣板機  畫出圖像
			canvas.repaint();
			
			//左上顯示檔案來源及頁數
			setTitle("圖像瀏覽器-"+parentName+"-"+readFile.getName());
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "到盡頭了!");
		}
	}

	//override一下paint的畫法
	class MyCanvas extends Canvas{
		@Override
		public void paint(Graphics g) {
			//根據圖像的大小畫出  控制坐標的起點 讓圖片置中
			Graphics2D g2d = (Graphics2D)g;
			int x = (int)((canvas.getWidth()-w)/2); 
			int y = (int)((canvas.getHeight()-h)/2);
			g2d.drawImage(bi,Math.max(x, 0) ,Math.max(y, 0) , w, h, null);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new MyACD();
	}
}
