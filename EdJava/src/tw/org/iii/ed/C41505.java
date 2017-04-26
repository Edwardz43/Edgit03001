package tw.org.iii.ed;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class C41505 extends JFrame{
	private MyClock myclock;
//	private MyPool mypool;
//	private MyPool2 mypool;
	private MyPool3 mypool;
	C41505(){
		setLayout(new BorderLayout());
		
		MyClock myclock = new MyClock();
		add(myclock, BorderLayout.NORTH);
//		MyPool mypool = new MyPool();
//		MyPool2 mypool = new MyPool2();
		MyPool3 mypool = new MyPool3();
		add(mypool, BorderLayout.CENTER);
		setSize(640,  480);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	
	public static void main(String[] args) {
		new C41505();
	}
}
