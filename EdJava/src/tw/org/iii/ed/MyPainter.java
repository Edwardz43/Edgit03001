package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyPainter extends JPanel {//implements MouseListener{
	private int x0, y0, x1, y1, w, h;
	private MyMouseAdapter myMouseAdapter;
	public MyPainter(){
		setBackground(Color.white);
		//addMouseListener(this);
		myMouseAdapter = new MyMouseAdapter(this);
		addMouseListener(myMouseAdapter);
		x0 = y0 = x1 = y1 = -1;
		
	}
	void setX0(int x0){this.x0=x0;}
	void setY0(int y0){this.y0=y0;}
	void setX1(int x1){this.x1=x1;}
	void setY1(int y1){this.y1=y1;}
//	void setww(int w){this.w=w;}
//	int getww(int w){return w;}
//	void sethh(int h){this.h=h;}
//	int gethh(int h){return h;}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		int w=80,h=80;
		g2d.setStroke(new BasicStroke(20));
//		for(int i=0; i<10;i++){
			g2d.setColor(Color.red);
			g2d.drawLine(x0, y0, x1, y1);
			//g2d.drawOval(x0, y0, w, h);
			//g2d.draw3DRect(x+i,y+i,w-i*2,h-i*2, true);
			//g2d.setColor(Color.yellow);
			//g2d.fill3DRect(x0+i-w/2,y0+i-h/2,w-2*i,h-2*i, true);
			//g2d.fill3DRect(x0+i-w/2,y0+i-h/2,w-2*i,h-2*i, true);
		//}
		
//		g2d.setColor(Color.red);
//		g2d.drawLine(0, 400, 1000, 100);
//		System.out.println("paint");
	}
	
	void changeLine(int x1, int y1){
		this.x1=x1; this.y1=y1;
		int r = Math.abs(x0-x1);
		w = h =2*r;
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		//System.out.println("clicked");
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		//x0 = e.getX(); y0 = e.getY();
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		//x1 = e.getX(); y1 = e.getY();  
//		//repaint();
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
}



class MyMouseAdapter extends MouseAdapter{
	private MyPainter painter;
	public MyMouseAdapter(MyPainter painter){
		this.painter = painter;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		painter.setX0(e.getX());
		painter.setY0(e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		painter.setX1(e.getX());
		painter.setY1(e.getY());  
		painter.repaint();
	}
}
