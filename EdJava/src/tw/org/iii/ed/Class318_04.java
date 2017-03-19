package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Class318_04 extends JFrame{
	private JButton open, save, exit;
	private MyPainter myPainter;
	
	public Class318_04(){
		// super();
		super("視窗程式");
		setLayout(new BorderLayout());
		
		open = new JButton("open");
		save = new JButton("save");
		exit = new JButton("exit");
		myPainter = new MyPainter();
		
		JPanel top = new JPanel(new FlowLayout());
		top.add(open); top.add(save); top.add(exit);
		
		add(top, BorderLayout.NORTH);
		add(myPainter, BorderLayout.CENTER);
		
		//add(open); add(save); add(exit);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Class318_04();
	}
}
