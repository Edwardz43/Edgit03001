package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

//聊天程式 v1.0
//目前就先跟自己聊天
public class ChatRoom extends JFrame{
	JPanel top, bottom;
	JTextField tf;
	JTextArea ta;
	JButton input;
	JScrollPane sp;
	ArrayList<String> recycle;
	int line;
	public ChatRoom (){
		super("ChatRoom");
		setLayout(new BorderLayout());
		
		//上面放一條label 好看的
		top = new JPanel();
		JLabel label = new JLabel("聊天程式 v1.0");
		top.add(label);
		
		//中間的主角 聊天的內容
		ta = new JTextArea("");
		//設定不能輸入  只供觀看
		ta.setEditable(false);
		//BGC
		ta.setBackground(Color.cyan);
		//顯示字體跟大小
		ta.setFont(new Font("微軟正黑體",Font.ROMAN_BASELINE,18));
		//加一個可以滑動的功能  水平滑動不需要  所以NEVER
		sp = new JScrollPane(ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//底層  有textfield可輸入內容  有一個button可以按下後輸入
		bottom = new JPanel();
		tf = new JTextField();
		
		//設定可以輸入多少字(寬度)
		tf.setColumns(50);
		
		//加入滑鼠跟按鍵監聽
		MyMouseListener listener = new MyMouseListener();
		MyKeyListener keyListener = new MyKeyListener();
		tf.addMouseListener(listener);
		tf.addKeyListener(keyListener);
		
		//設定按鈕功能
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
		
		//加入一個資源回收桶  收集之前輸入過的內容 等等會用到
		//因為無法預期大小 所以用list
		recycle = new ArrayList<String>(); 
		
		//接收訊息  基本上是無限迴圈
		receive();
	}
	//接收訊息  基本上都是上課的內容  
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
					//將接收到的訊息以String顯示在textarea上 
					//因為要保留舊的內容  所以新的內容(line)要接在原有內容(ta.getText)後面
					ta.setText(ta.getText()+line+"\n");
				}
				br.close();
				server.close();
				//System.out.println("ok");
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}	
	}
	
	//發送端  也是上課內容
	protected void senInput() {
		try {
			//先擷取textfield的內容  準備送出去
			String input = tf.getText();
			
			//傳給自己測試
			Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
				
			OutputStream out = socket.getOutputStream();
			out.write(input.getBytes());
			out.flush();
			out.close();	
			socket.close();
			
			//每當有新的內容輸入  就複製一份在資源回收桶
			recycle.add(input);
			//設定一個int = index,  相當於資源回收桶的大小-1(index從0開始)
			line = recycle.size()-1;
			//輸入完送出訊息後  將textfield清空
			tf.setText("");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	//加入一個功能 希望每次滑鼠點擊textfield時 內容會清空  方便下一次輸入
	private class MyMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			tf.setText("");
		}
	}
	
	//加入一些聊天常用功能  按enter可以輸入  按"上"可以顯示上一次輸入的內容  按"下"則反
	private class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent ee){
			int n = ee.getKeyCode();
			switch (n){
				//如果按enter 呼叫輸入
				case KeyEvent.VK_ENTER:
					//加一個判斷  textfield有東西  按下enter才輸入
					if(!tf.getText().equals("")){
						senInput();
					}
					break;
				//如果按下方向鍵的上  顯示上一次的輸入內容	
				case KeyEvent.VK_UP:
					tf.setText(recycle.get(line));
					line=(line>1)?line-1:0;
					break;
				//如果按下方向鍵的下  顯示下一次輸入'的內容	
				case KeyEvent.VK_DOWN:
					tf.setText(recycle.get(line+1));
					line=(line<recycle.size()-2)?line+1:line;
					break;
			}
		}		
	}
	
	public static void main(String[] args) {
		new ChatRoom(); 
	}
}
