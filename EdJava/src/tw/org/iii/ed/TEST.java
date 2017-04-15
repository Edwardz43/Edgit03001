package tw.org.iii.ed;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TEST extends JFrame{
	private PointerClock pointerclock;
	private TEST(){
		super("Pointer Clock");
		setLayout(new BorderLayout());
		
		pointerclock = new PointerClock();
		add(pointerclock, BorderLayout.CENTER);
		
		setSize(800, 640);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
    public static void main(String[] args) {
    	new TEST();
    }
 }


