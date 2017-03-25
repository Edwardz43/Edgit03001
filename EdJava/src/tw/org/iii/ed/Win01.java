package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Win01 extends JFrame{
	private MyPainter myPainter;
	
	public Win01(){
		super("Welcome MyPainter");
		setLayout(new BorderLayout());
		myPainter = new MyPainter();
		JPanel top = new JPanel(new BorderLayout());
		
		add(top, BorderLayout.NORTH);
		add(myPainter, BorderLayout.CENTER);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		new Win01();
	}
}

