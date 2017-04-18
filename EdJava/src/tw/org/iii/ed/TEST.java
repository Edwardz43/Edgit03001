package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class TEST extends JFrame{
	private PointerClock pointerclock;
	private CalendarwithJFrame calendar;
	private TEST(){
		super("Pointer Clock");
		setLayout(new BorderLayout());
		pointerclock = new PointerClock();
		add(pointerclock);
		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
    public static void main(String[] args) {
    	new TEST();
    }
 }


