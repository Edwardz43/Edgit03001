package tw.org.iii.ed;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Training99Frame extends JFrame{
	//private MyClock myclock;
//	private MyPool mypool;
	private MyPool2 mypool;
//	private MyPool3 mypool;
	Training99Frame(){
		setLayout(new BorderLayout());
		
		//MyClock myclock = new MyClock();
		//add(myclock, BorderLayout.NORTH);
//		MyPool mypool = new MyPool();
		MyPool2 mypool = new MyPool2();
//		MyPool3 mypool = new MyPool3();
		add(mypool, BorderLayout.CENTER);
		setSize(800,  600);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	
	public static void main(String[] args) {
		new Training99Frame();
	}
}
