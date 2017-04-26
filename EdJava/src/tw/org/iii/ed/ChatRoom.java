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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*聊天程式 v1.1
 * 新增:
 * 1. 可輸入連線IP
 * 2. TextArea => TextPane 可更改文字顏色
 */ 
public class ChatRoom extends JFrame{
	private JPanel top, bottom, sidebar1, sidebar2;
	private JTextField tf;
	private JTextPane ta;
	private JButton input, connect, changeColor;
	private JScrollPane sp;
	private ArrayList<String> recycle;
	private String ip;
	private Color mycolor;
	private int line;
	
	public ChatRoom (){
		super("ChatRoom");
		setLayout(new BorderLayout());
		
		//上面放一條label 好看的
		top = new JPanel();
		JLabel label = new JLabel("聊天程式 v1.0");
		top.add(label);
		
		//中間的主角 聊天的內容
		ta = new JTextPane();
		//設定不能輸入  只供觀看
		//ta.setEditable(false);
		//BGC
		ta.setBackground(Color.cyan);
		//顯示字體跟大小
		ta.setFont(new Font("微軟正黑體",Font.ROMAN_BASELINE,18));
		//加一個可以滑動的功能  水平滑動不需要  所以NEVER
		sp = new JScrollPane(ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		sidebar1 = new JPanel();
		sidebar2 = new JPanel();
		
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
		
		//設定按鈕功能  視用lambda與法
		input= new JButton("輸入");
		input.addActionListener(e -> {
			senInput();
		});
		
		//連接
		connect= new JButton("連接");
		connect.addActionListener(e ->{
			connect();
		});
		
		changeColor = new JButton("選擇顏色");
		changeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeColor();
			}
		});
				
		bottom.add(tf);bottom.add(input);bottom.add(connect);bottom.add(changeColor);
		
		add(top, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
		add(sidebar1, BorderLayout.EAST);
		add(sidebar2, BorderLayout.WEST);
		add(bottom, BorderLayout.SOUTH);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//加入一個資源回收桶  收集之前輸入過的內容 等等會用到
		//因為無法預期大小 所以用list
		recycle = new ArrayList<String>();
		mycolor = Color.BLACK;
		//接收訊息  基本上是無限迴圈
		receive();
	}
	protected void changeColor() {
		mycolor = new JColorChooser().showDialog(this, "選擇顏色", Color.black);
		if(mycolor == Color.WHITE || mycolor == Color.white){
			changeColor.setBackground(mycolor);
			changeColor.setForeground(Color.BLACK);
		}else if(mycolor == Color.BLACK || mycolor == Color.black){
			changeColor.setBackground(mycolor);
			changeColor.setForeground(Color.WHITE);
		}else{
			changeColor.setForeground(Color.BLACK);
			changeColor.setBackground(mycolor);
		}
	}
	private void connect() {
		try {
			ip = JOptionPane.showInputDialog("請輸入連接端的IP:");
			//檢查ip是否符合IPv4
			if(ip.matches("^(([2][0-4][0-9]|[2][5][0-5]|[01]?[0-9]?[0-9])[.]){3}([2][0-4][\\d]|[2][5][0-5]|[01]?[\\d][\\d]?)$")){
				String test = "";
				
				//嘗試連接對方
				Socket socket = new Socket(InetAddress.getByName(ip), 1234);
				OutputStream out = socket.getOutputStream();
				out.write(test.getBytes());
				out.flush();
				out.close();	
				socket.close();
				
				//若連接成功  印出訊息
				//ta.setForeground(Color.red);
				ta.setText(ta.getText()+"開始聊天!\n");
			
			}else{
				JOptionPane.showMessageDialog(null, "請輸入正確IP格式!");
			}
		} catch (Exception e) {
			//失敗  印出錯誤訊息
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
	}
	
	
	//接收訊息  基本上都是上課的內容  
	private void receive() {
		for(;;){
			try {
				ServerSocket server = new ServerSocket(1234);
				
				Socket socket = server.accept();
				//System.out.println(socket.getInetAddress().getHostAddress());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				
				String line;
				while((line = br.readLine()) != null){
					//將接收到的訊息以String顯示在textarea上 
					//因為要保留舊的內容  所以新的內容(line)要接在原有內容(ta.getText)後面
					appendToPane(ta,line,mycolor);
					//ta.setText(ta.getText()+line+"\n");
				}
				br.close();
				server.close();
				//System.out.println("ok");
			} catch (Exception e) {
				appendToPane(ta,e.toString(),Color.RED);
			}
		}	
	}
	
	//發送端  也是上課內容
	protected void senInput() {
		try {
			//先擷取textfield的內容  準備送出去
			String input = tf.getText();
			//將自己輸入的內容印在銀幕上
			appendToPane(ta,input,mycolor);
			
			//取得對方IP
			Socket socket = new Socket(InetAddress.getByName(ip), 1234);
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
			appendToPane(ta,e.toString(),Color.RED);
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
	
	private void appendToPane(JTextPane ta, String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = ta.getDocument().getLength();
        ta.setCaretPosition(len);
        ta.setCharacterAttributes(aset, false);
        ta.replaceSelection(msg+"\n");
    }
	
	public static void main(String[] args) {
		new ChatRoom(); 
	}
}
