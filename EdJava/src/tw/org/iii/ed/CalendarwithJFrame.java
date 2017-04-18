package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class CalendarwithJFrame extends JLabel{
	int y, m, d;
	private JButton Year, Month, Day;
	private JTextArea textarea;	
	
	public CalendarwithJFrame(){
		super("萬年曆");
		setLayout(new BorderLayout());		
		JPanel jp = new JPanel();
		Calendar c = Calendar.getInstance();
		y = c.get(c.YEAR); m=c.get(c.MONTH)+1;d = c.get(c.DATE);
		System.out.println("y:"+y+",m :"+m+", d :"+d);
//		Year = new JButton("年曆");
//		Year.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				textarea.setText("");
//				try{
//					y = Integer.parseInt(JOptionPane.showInputDialog("輸入年分 :"));
//					yearCalendar(y);
//				}catch(Exception ee){
//					JOptionPane.showMessageDialog(null, "請重新輸入!");
//				}
//			}
//		});
		
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
		//jp.add(Year); 
		jp.add(Month);jp.add(Day);
		add(jp, new BorderLayout().NORTH);
		textarea.setBackground(Color.LIGHT_GRAY);
		textarea.setLineWrap(true);
		textarea.setFont(new Font("微軟正黑體", Font.ROMAN_BASELINE, 16));
		JScrollPane span = new JScrollPane(textarea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		dayCalendar(y, m, d);
		
		add(span, new BorderLayout().CENTER);
		setVisible(true);
		setSize(800, 640);
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

//	public void yearCalendar(int y){
//		textarea.append("列印年份: "+y+"\n");
//		for(int k=1;k<13;k++){
//			int w = week(y,k,1);
//			int d = setDays(y,k);
//			textarea.append(k+"月\n");
//			textarea.append("\n");
//			String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
//			for(String v :week){
//				textarea.append(v+"\t");
//			}
//			textarea.append("\n");
//			int[][] calendar = new int[6][7];
//			int[] setCalendar = new int[42];
//			for(int i=0;i<d;i++){
//				setCalendar[w+i]=1;
//			}
//			
//			for(int i=0; i<setCalendar.length; i++){
//				calendar[i/7][i%7]=setCalendar[i];
//			}
//			
//			int days = 1;
//			for(int i=0;i<6;i++){
//				for(int j=0;j<7;j++){
//					if(calendar[i][j] == 0){
//						textarea.append("\t");
//					}else if(days<10){
//						textarea.append("  "+(days++)+"\t");
//					}else{
//						textarea.append(" "+(days++)+"\t");
//					}
//				}
//				textarea.append("\n");
//			}
//			textarea.append("\n");
//		}
//	}	

	public void monthCalendar(int y, int m){
		int w = week(y,m,1);
		int d = setDays(y,m);
		
		textarea.append("列印月份: "+y+"年"+m+"月\n");
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
		
		textarea.append("列印月份: "+y+"年"+m+"月\n");
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
					//if(days==date)textarea.append("*");
					textarea.append((days==date)?"*"+(days++)+"\t":" "+(days++)+"\t");
				}
			}
			textarea.append("\n");
		}	
	}


	public static void main(String[] args){
			CalendarwithJFrame t1 = new CalendarwithJFrame();
		
	}
}

