package tw.org.iii.ed;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class DatePrinter extends JFrame{
	private AnalogyClock pointerclock;
	private deskCalendar calendar;
	private DatePrinter(){
		super("Pointer Clock");
		setLayout(new GridLayout(2, 1));
		calendar = new deskCalendar();
		pointerclock = new AnalogyClock();
		add(calendar);
		add(pointerclock);
		setSize(800, 840);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
    public static void main(String[] args) {
    	new DatePrinter();
    }
 }

//月曆 => 萬年曆的作業
class deskCalendar extends JLabel{
	int y, m, d;
	private JButton Month, Day;
	private JTextArea textarea;	
	
	public deskCalendar(){
		super("萬年曆");
		setLayout(new BorderLayout());		
		JPanel jp = new JPanel();
		Calendar c = Calendar.getInstance();
		y = c.get(c.YEAR); m=c.get(c.MONTH)+1;d = c.get(c.DATE);
		System.out.println("y:"+y+",m :"+m+", d :"+d);
		
		Month = new JButton("月曆");	
		Month.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textarea.setText("");
				try{
					y = Integer.parseInt(JOptionPane.showInputDialog("輸入年分 :"));
					m = Integer.parseInt(JOptionPane.showInputDialog("輸入月分 :"));
					monthCalendar(y, m);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "請重新輸入!");
				}
			}
		});
		
		Day = new JButton("查日期");
		Day.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textarea.setText("");
				try{	
					y = Integer.parseInt(JOptionPane.showInputDialog("輸入年分 :"));
					m = Integer.parseInt(JOptionPane.showInputDialog("輸入月分 :"));
					d = Integer.parseInt(JOptionPane.showInputDialog("輸入日期 :"));
					dayCalendar(y, m, d);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "請重新輸入!");
				}
			}
		});
		
		textarea = new JTextArea();			
		jp.add(Month);jp.add(Day);
		new BorderLayout();
		add(jp, BorderLayout.NORTH);
		textarea.setBackground(Color.CYAN);
		textarea.setLineWrap(true);
		textarea.setFont(new Font("微軟正黑體", Font.ROMAN_BASELINE, 16));
		
		//預先印出當天資料
		dayCalendar(y, m, d);
		
		new BorderLayout();
		add(textarea, BorderLayout.CENTER);
	}
	
	static int week(int y, int m, int d){
		int C = y/100; int Y = (y%100); int M = m-2;
		if(m==1){
			Y=Y-1;M=11;
		}
		if(m==2){
			Y=Y-1;M=12;
		}
		int W =(d + (int)(2.6*M -0.2)+5*(Y%4)+3*Y+5*(C%4))%7; 
		return W;
	}
	static int setDays(int y ,int m){
		int d = 0;
		switch(m){
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			d = 31; break;
		case 4: case 6: case 9: case 11:
			d = 30; break;
		case 2:
			if(leapYear(y)){
				d = 29; 
			}else{
				d = 28;
			}
		}
		return d;
	}
	static boolean leapYear(int year){
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
			return true;
		} 
	    return false;
	}

	public void monthCalendar(int y, int m){
		int w = week(y,m,1);
		int d = setDays(y,m);
		
		textarea.append(y+"年"+m+"月\n");
		textarea.append("\n");
		String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(String v :week){
			textarea.append(v+"\t");
		}
		textarea.append("\n");
		int[][] calendar = new int[6][7];
		int[] setCalendar = new int[42];
		for(int i=0;i<d;i++){
			setCalendar[w+i]=1;
		}
		
		for(int i=0; i<setCalendar.length; i++){
			calendar[i/7][i%7]=setCalendar[i];
		}
		
		int days = 1;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(calendar[i][j] == 0){
					textarea.append("\t");
				}else if(days<10){
					textarea.append("  "+(days++)+"\t");
				}else{
					textarea.append(" "+(days++)+"\t");
				}
			}
			textarea.append("\n");
		}	
	}

	public void dayCalendar(int y, int m, int date){
		int w = week(y,m,1);
		int d = setDays(y,m);
		
		textarea.append(y+"年"+m+"月\n");
		textarea.append("\n");
		String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(String v :week){
			textarea.append(v+"\t");
		}
		textarea.append("\n");
		int[][] calendar = new int[6][7];
		int[] setCalendar = new int[42];
		for(int i=0;i<d;i++){
			setCalendar[w+i]=1;
		}
		
		for(int i=0; i<setCalendar.length; i++){
			calendar[i/7][i%7]=setCalendar[i];
		}
		
		int days = 1;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(calendar[i][j] == 0){
					textarea.append("\t");
				}else if(days<10){
					textarea.append((days==date)?" *"+(days++)+"\t":"  "+(days++)+"\t");
				}else{
					textarea.append((days==date)?"*"+(days++)+"\t":" "+(days++)+"\t");
				}
			}
			textarea.append("\n");
		}	
	}
}

//時鐘 => 最後一次的作業
class AnalogyClock extends JPanel{
	private MyClockTask myclock;
	private Timer timer;
	private int viewW, viewH;
	private JLabel top;
	private int hh, mm, ss;
	String number[] = { "12 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10 ", "11 " };
	
	public AnalogyClock(){
		setLayout(new BorderLayout());
		//讓時鐘顯示在畫面正中間 所以先抓視窗的中點
		viewW = getWidth()/2; viewH = getHeight()/2;
		
		top = new JLabel();
		add(top, BorderLayout.NORTH);
		//設置timer
		timer = new Timer();
		myclock = new MyClockTask();
		
		//因為是根據calendar的時間 所以間隔取多少是無所謂
		timer.schedule(myclock, 1000 , 1000);	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//新增  讓時鐘跟著視窗縮放
		viewW = getWidth()/2; viewH = getHeight()/2;
		
		//背景色
		g2d.setColor(Color.CYAN);
		//填滿 記得*2
		g2d.fillRect(0, 0, viewW*2, viewH*2);
		
		//畫圓
		g2d.setStroke(new BasicStroke(8));
		g2d.setColor(Color.gray);
		//圓心位置要置中  半徑就和長的一半相等
		g2d.drawOval((int)(viewW-viewW/2), (int)(viewH-viewW/2), viewW, viewW);
		
		//畫刻度
		g2d.setStroke(new BasicStroke(3));
//		g2d.setColor(Color.black);
		
		//主要的技巧和指針一樣  每6度就畫一個  長度我是選擇10
		for(int i=0; i<60; i++){
			if(i%5==0){
				//5的倍數要長一點
				g2d.drawLine(cosPointer(i, viewW/2), sinPointer(i, viewW/2), 
						cosPointer(i, viewW/2-20), sinPointer(i, viewW/2-20));
			}else{
				g2d.drawLine(cosPointer(i, viewW/2), sinPointer(i, viewW/2), 
					cosPointer(i, viewW/2-10), sinPointer(i, viewW/2-10));
			}
		}
		
		//畫數字
		g2d.setFont(new Font(null, Font.ITALIC, 18));
		g2d.setColor(Color.BLACK);
		for(int i=0; i<12; i++){
			//微調位置  讓數字跟刻度對齊
			g2d.drawString(number[i], cosPointer(i*5, viewW/2-40)-9, sinPointer(i*5, viewW/2-40)+7);
		}
		
		//秒針
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.red);
		g2d.drawLine(viewW, viewH, cosPointer(ss, viewW/3), sinPointer(ss, viewW/3));
		
		//分針
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.blue);
		g2d.drawLine(viewW, viewH, cosPointer(mm, viewW/4), sinPointer(mm, viewW/4));
		
		//時針  修正bug: 計算角度時 加上分鐘的角度  以免時針不動  
		//=> 簡單原理: 時針一小時移動30度  所以每分鐘移動0.5度  所以用(h*5+m*0.5/6)*6  記得轉成int
		g2d.setStroke(new BasicStroke(7));
		g2d.setColor(Color.green);
		g2d.drawLine(viewW, viewH, cosPointer((int)(hh*5+mm*0.5/6), viewW/5), sinPointer((int)(hh*5+mm*0.5/6), viewW/5));	
	}
	
	 /*設兩個方法  輸入時間(n) 半徑(r)  就可以回傳指針移動的軌跡(x, y)
	 * 主要就是高中數學的極坐標  任一點坐標可用原點坐標加上(r*cosX, r*sinX)來表示  r是半徑  X是角度
	 * 因為時鐘從12點開始算  和一般直角坐標不同  所以+90度
	 * 但程式語言的作標和一般直角坐標上下顛倒  所以變成-90度(就是差180度的概念)
	 * => 化簡為  只要輸入時間以及指針長短  就可以畫出來
	 */ 
	public int cosPointer(int time, int r){
		return viewW+(int)((Math.cos(Math.toRadians(time*6.0-90)))*r);
	}
	public int sinPointer(int time, int r){
		return viewH+(int)((Math.sin(Math.toRadians(time*6.0-90)))*r);
	}
	//數位時鐘	
	public class MyClockTask extends TimerTask{
		@Override
		public void run() {
			Calendar now = Calendar.getInstance();
			hh =now.get(Calendar.HOUR);
			mm = now.get(Calendar.MINUTE);
			ss = now.get(Calendar.SECOND);
			//設定數位時鐘
			top.setFont(new Font(null, Font.ITALIC, 28));
			top.setText(hh+":"+ mm+":"+ss);
			repaint();
		}
	}
}


