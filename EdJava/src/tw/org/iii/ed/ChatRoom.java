package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatRoom extends JFrame{
	JPanel top, bottom;
	JTextField tf;
	JTextArea ta;
	JButton input;
	JScrollPane sp;
	ArrayList<String> recycle;
	public ChatRoom (){
		super("ChatRoom");
		setLayout(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel();
		
		ta = new JTextArea("");
		ta.setEditable(false);
		ta.setBackground(Color.cyan);
		ta.setFont(new Font("標楷體",Font.ROMAN_BASELINE,18));
		
		sp = new JScrollPane(ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		MyMouseListener listener = new MyMouseListener();
		MyKeyListener keyListener = new MyKeyListener();
		tf = new JTextField();
		tf.setColumns(50);
		tf.addMouseListener(listener);
		tf.addKeyListener(keyListener);
		
		input= new JButton("輸入");
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				senInput();
			}
		});
		
		bottom.add(tf);
		bottom.add(input);
		
		
		add(top, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		recycle = new ArrayList<String>(); 
		receive();
	}

	private void receive() {
		for(;;){
			try {
				ServerSocket server = new ServerSocket(9999);
				
				Socket socket = server.accept();
				//System.out.println(socket.getInetAddress().getHostAddress());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				
				String line;
				while((line = br.readLine()) != null){
					ta.setText(ta.getText()+line+"\n");
				}
				br.close();
				server.close();
				//System.out.println("ok");
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		
	}

	protected void senInput() {
		try {
			String input = tf.getText();
			
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
				
			OutputStream out = socket.getOutputStream();
			out.write(input.getBytes());
			out.flush();
			out.close();	
			socket.close();
		
			recycle.add(input);
			tf.setText("");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			tf.setText("");
		}
	}
	
	private class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent ee){
			if(ee.getKeyCode()==KeyEvent.VK_ENTER){
				if(tf.getText()!="")
				//System.out.println("enter");
				senInput();
			}else if(ee.getKeyCode()==KeyEvent.VK_UP){
				int n = recycle.size()-1;
				tf.setText(recycle.get(n));
			}
		}
		
	}
	
	public static void main(String[] args) {
		new ChatRoom(); 
	}
}
