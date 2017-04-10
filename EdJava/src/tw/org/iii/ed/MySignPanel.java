package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

public class MySignPanel extends JPanel{
	private LinkedList<LinkedList<HashMap<String, Integer>>> lines, recycle;
	//每條線都有顏色與筆畫粗細  所以用HashMap存放很適合 
	//要把線依序編號 方便之後repaint  所以外面再加一層LinkedList
	private LinkedList<HashMap<String, Color>> color;
	private LinkedList<HashMap<String, Integer>> stroke;
	
	//為了之後的方便  先宣告兩個全區變數  myColor mtStroke 並且給它們初始化
	private Color myColor;
	private int myStroke;
	
	public MySignPanel(DigitalSign ds){
		setBackground(Color.yellow);
		
		//在這邊 先暫定藍色  粗細5
		myColor =  Color.BLUE;
		myStroke = 5;
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		myColor =  Color.BLUE;
		myStroke = 5;
		
		lines = new LinkedList<>();
		recycle = new LinkedList<>();
		
		//弄兩個新的  等等要拿來存color跟stroke
		color = new LinkedList<>();
		stroke = new LinkedList<>();

		
	}
	// 1. mouse event, 2. data structure, 3. draw
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		//很重要 因為要決定每條線的粗細與顏色  需要每條線的編號(index)
		//所以不用foreach 改用傳統的for 
		for(int j=0; j<lines.size(); j++){
			//叫出線的顏色
			HashMap<String, Color> cOl0 = color.get(j);
			g2d.setColor(myColor= cOl0.get("color"));
			
			//叫出粗細
			HashMap<String, Integer> sOl0 = stroke.get(j);
			g2d.setStroke(new BasicStroke(myStroke = sOl0.get("stroke")));
			
			//畫線
			LinkedList<HashMap<String, Integer>> line = lines.get(j);
			for(int i = 1 ; i<line.size(); i++){
				HashMap<String, Integer> p0 = line.get(i-1);
				HashMap<String, Integer> p1 = line.get(i);
				g2d.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"));
			}
		}
				
	}
	
	//變色
	public void changeColor(Color color){
		myColor=color;
	}
	//變粗細
	public void changeStroke(int stroke) {
		myStroke = stroke;	
	}
	
	public void clear (){
		lines.clear();
		repaint();
	}
	
	public void undo(){
		if(lines.size()>0){
			recycle.add(lines.removeLast());
			repaint();
		}
	}
	public void redo(){
		if(recycle.size()>0){
			lines.add(recycle.removeLast());
			repaint();
		}
	}
	
	//變背景顏色
	public void changeBGC(Color bgc) {
		setBackground(bgc);
	}
	
	//很重要的來了  因為線的粗細跟顏色  是在你滑鼠按下的那一刻就決定了 
	//所以我們在這邊記錄它的顏色根粗細
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
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
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			HashMap<String, Integer> point = new HashMap<>();
			point.put("x", e.getX());
			point.put("y", e.getY());
			lines.getLast().add(point);
			repaint();
		}
	}
}
