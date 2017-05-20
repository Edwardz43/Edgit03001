package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Signature extends JFrame{
	private JButton clear, undo, redo, color, stroke, BGC, save, open, export, capture;
	private MySign msp;
	
	public Signature(){
		super("Signature");
		setLayout(new BorderLayout());
		
		clear= new JButton("清空");undo= new JButton("undo");redo= new JButton("redo");stroke= new JButton("粗細");
		color =  new JButton("顏色");BGC= new JButton("背景顏色");save= new JButton("儲存");open= new JButton("開啟檔案");export= new JButton("輸出圖檔");
		capture = new JButton("截圖");
		
		JPanel top = new JPanel(new FlowLayout());
		JPanel bottom = new JPanel(new FlowLayout());
		
		top.add(save);top.add(open);top.add(export);top.add(capture);
		bottom.add(clear);bottom.add(undo);bottom.add(redo);bottom.add(BGC);bottom.add(color);bottom.add(stroke);
		
		add(top, BorderLayout.NORTH); 
		add(bottom, BorderLayout.SOUTH);
		msp = new MySign(this);
		add(msp, BorderLayout.CENTER);
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.clear();
			}
		});
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.undo();
			}
		});
		redo.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.redo();
				
			}
		});
		
		//換顏色的按鈕
		color.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.changeColor();
			}
		});
		
		//換粗細的按鈕
		stroke.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.changeStroke();
			}
		});
		
		//換背景顏色的按鈕
		BGC.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Color bgc = JColorChooser.showDialog(Signature.this, "Select a Color :", Color.yellow);
				msp.changeBGC(bgc);
			}
		});
		
		//儲存簽名
		save.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.saveFile();
			}
		});
		
		//開啟舊檔
		open.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.openFile();
			}
		});
		
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.exportFile();
			}
		});
		
		capture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.setCap();
			}
		});
		
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Signature();
	}
}

class MySign extends JPanel{
	private LinkedList<LinkedList<HashMap<String, Integer>>> lines, recycle;
	private boolean isCapture;
	//每條線都有顏色與筆畫粗細  所以用HashMap存放很適合 
	//要把線依序編號 方便之後repaint  所以外面再加一層LinkedList
	//修正bug:新增colorRecycle strokeRecycle 避免在undo redo clear時 line與color stroke不同步的錯誤
	private LinkedList<HashMap<String, Color>> color, colorRecycle;
	private LinkedList<HashMap<String, Integer>> stroke, strokeRecycle;
	private ArrayList<LinkedList> sign;
	
	//為了之後的方便  先宣告兩個全區變數  myColor mtStroke 並且給它們初始化
	private Color myColor;
	private int myStroke;
	
	public MySign(Signature ds){
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		lines = new LinkedList<>();
		recycle = new LinkedList<>();
		
		//弄幾個新的LinkedList 等等要拿來存color跟stroke
		color = new LinkedList<>();
		colorRecycle = new LinkedList<>();
		stroke = new LinkedList<>();
		strokeRecycle = new LinkedList<>();
		
		//拿來存簽名檔的  可序列化  方便存取
		sign = new ArrayList();
		
		isCapture = false;
		//在這邊先暫定 背景淺灰色  筆畫黑色  粗細5
		setBackground(Color.lightGray);
		myColor =  Color.BLACK;
		myStroke = 5;
		
	}
	// 1. mouse event, 2. data structure, 3. draw
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//很重要 因為要決定每條線的粗細與顏色  需要每條線的編號(index)
		//所以不用foreach 改用傳統的for
		if(!isCapture){
			for(int j=0; j<lines.size(); j++){
				//叫出線的顏色
				HashMap<String, Color> cOl0 = color.get(j);
				g2d.setColor(myColor= cOl0.get("color"));
				
				//叫出粗細
				HashMap<String, Integer> sOl0 = stroke.get(j);
				g2d.setStroke(new BasicStroke(myStroke = sOl0.get("stroke")));
				
				//畫線
				LinkedList<HashMap<String, Integer>> line = lines.get(j);
				//System.out.println(myColor); 
				//測試用的
				for(int i = 1 ; i<line.size(); i++){
					HashMap<String, Integer> p0 = line.get(i-1);
					HashMap<String, Integer> p1 = line.get(i);
					g2d.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"));
				}
			}
		}else{
			
			
			
		}
				
	}
	
	//變色
	public void changeColor(){
		myColor= JColorChooser.showDialog(this, "Select a Color :", Color.BLUE);
		//System.out.println(myColor);
		//測試用的
	}
	//變粗細
	public void changeStroke() {
		//用JOP來輸入粗細  加個try/catch避免輸入錯誤
		try{
			myStroke = Integer.parseInt(JOptionPane.showInputDialog("輸入筆畫粗細 :"));
		}catch(Exception ee){
			JOptionPane.showMessageDialog(null, "請輸入正確數字!");
		}
	}
	
	//清空  三個都清
	public void clear (){
		lines.clear();
		color.clear();
		stroke.clear();
		repaint();
	}
	
	
	//一樣 三個同步
	public void undo(){
		if(lines.size()>0){
			recycle.add(lines.removeLast());
			colorRecycle.add(color.removeLast());
			strokeRecycle.add(stroke.removeLast());
			repaint();
		}
	}
	
	//同上
	public void redo(){
		if(recycle.size()>0){
			lines.add(recycle.removeLast());
			color.add(colorRecycle.removeLast());
			stroke.add(strokeRecycle.removeLast());
			repaint();
		}
	}
	
	//變背景顏色
	public void changeBGC(Color bgc) {
		setBackground(bgc);
	}
	
	//檔案總管的應用: 紀錄檔案
	//這邊我選擇用一個普通的ArrayList 來存放簽名檔 
	//裡面會有  筆畫  回收桶  粗細 顏色   四種資料 都是LinkedList
	//存檔的時候依序存入 因為是List 有支援序列化  所以用ObjectOutputStream
	public void saveFile() {
		JFileChooser fc = new JFileChooser("./dir2");
		int option = fc.showSaveDialog(null);
		if(option == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			sign.add(lines);sign.add(recycle);sign.add(stroke);sign.add(color);
			try {
				ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
				oout.writeObject(sign);
				oout.flush();
				oout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//讀取簽名檔 一樣是作業的應用
	//開啟後記得轉回原型:ArrayList<LinkedList> 然後【依序】取出裡面的資料(順序不能亂)
	public void openFile() {
		JFileChooser fc = new JFileChooser("./dir2");
		int option = fc.showOpenDialog(null);
		if(option == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			try {
				ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
				Object obj = oin.readObject();
				oin.close();
				sign = (ArrayList<LinkedList>)obj;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lines = sign.get(0);recycle = sign.get(1);stroke = sign.get(2);color = sign.get(3);
		repaint();
		
	}
	//新增功能:輸出程圖檔
	protected void exportFile() {
		try{
			JFileChooser fc = new JFileChooser("./dir2");
			int option = fc.showSaveDialog(null);
			if(option == JFileChooser.APPROVE_OPTION){
				File saveFile = fc.getSelectedFile();
				//簡單一行  寫入 (目標影像, 格式, 目的地)
				BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = (Graphics2D)bi.getGraphics();
				this.paintAll(g2d);
				ImageIO.write(bi, "png", saveFile);	
			}
		}catch(Exception ee){
				ee.toString();
		}		
	}
	
	//
	public void setCap(){
		this.isCapture = true;
	} 
	
	//很重要的來了  因為線的粗細跟顏色  是在你滑鼠按下的那一刻就決定了 
	//所以我們在這邊記錄它的顏色根粗細
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			if(!isCapture){
				super.mousePressed(e);
				
				recycle.clear();
				LinkedList<HashMap<String, Integer>> line = new LinkedList<>();
				
				HashMap<String, Integer> point = new HashMap<>();
				point.put("x", e.getX());
				point.put("y", e.getY());
				line.add(point);
			
				lines.add(line);
				
				//紀錄線的顏色
				HashMap<String, Color> colorOfLine = new HashMap<>();
				colorOfLine.put("color", myColor);
				color.add(colorOfLine);
				
				//紀錄線的粗細
				HashMap<String, Integer> strokeOfLine = new HashMap<>();
				strokeOfLine.put("stroke", myStroke);
				stroke.add(strokeOfLine);
			}else{
				
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(!isCapture){
				super.mouseDragged(e);
				HashMap<String, Integer> point = new HashMap<>();
				point.put("x", e.getX());
				point.put("y", e.getY());
				lines.getLast().add(point);
				repaint();
			}else{
				
			}
		}
	}	
}


