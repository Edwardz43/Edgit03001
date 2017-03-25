package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyPainter_v2 extends JPanel {
	private int x0, y0, x1, y1, w, h;
	private MyMouseAdapter myMouseAdapter;
	public MyPainter_v2(){
		setBackground(Color.white);
		addMouseListener(new MyMouseAdapter());
		x0 = y0 = x1 = y1 = -1;
		
	}
	void setX0(int x0){this.x0=x0;}
	void setY0(int y0){this.y0=y0;}
	void setX1(int x1){this.x1=x1;}
	void setY1(int y1){this.y1=y1;}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(20));
			g2d.setColor(Color.red);
			g2d.drawLine(x0, y0, x1, y1);
	}
	
	void changeLine(int x1, int y1){
		this.x1=x1; this.y1=y1;
		int r = Math.abs(x0-x1);
		w = h =2*r;
	}
	private class MyMouseAdapter extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			x0 = e.getX(); y0 = e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			x1 = e.getX(); y1 = e.getY(); 
			repaint();
			
		}
	}
	
	
}
