package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatRoom {
	public static void main(String[] args) {
		Chat c = new Chat(); 
	}
}
class Chat extends JFrame{
	JPanel top, bottom;
	JTextField tf;
	JTextArea ta;
	JButton input;
	
	public Chat (){
		super("ChatRoom");
		setLayout(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel();
		ta = new JTextArea("");
		tf = new JTextField();
		input= new JButton("輸入");
		
		bottom.add(tf);
		bottom.add(input);
		
		add(top, BorderLayout.NORTH);
		add(ta, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
