package tw.org.iii.ed;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Training99Frame extends JFrame{
	//private MyClock myclock;
//	private MyPool mypool;
//	private Training99 t99;
//	private MyPool3 mypool;
	Training99Frame(){
		setLayout(new BorderLayout());
		Training99 t99 = new Training99();
		add(t99, BorderLayout.CENTER);
		setSize(800,  600);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	
	public static void main(String[] args) {
		new Training99Frame();
	}
}
