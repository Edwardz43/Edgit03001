package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class HW312_calendar_v2 {  
	
	public static void main(String[] args){
		try{
			
			int year=0;
			int month=0;
			String Y = JOptionPane.showInputDialog("請輸入年分:\n(格式:yyyy)");
			year = Integer.parseInt(Y);
			String M = JOptionPane.showInputDialog("請輸入月分:\n(格式:mm)");
			month = Integer.parseInt(M);
			
			//1. 利用公式找出每月的第一天
			int w = week(year,month);
			
			//2. 決定天數
			int d =setDays(year,month);
			
			// 預先列出layout
			System.out.println("列印月份: "+year+"年"+month+"月");
			String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
			for(String v :week){
				System.out.print(v+"\t");
			}
			System.out.println();
			
			//3. 印出月曆
			printCalendar(w,d);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "輸入錯誤,程式即將終止!\n請稍後重啟程式");
		}
	}
	
	//1. 找出firstDay_of_Month
	static int week(int y, int m){
		int C = y/100;
		int Y = (y%100);
		int M = m-2;
		if(m==1){
			Y=Y-1;
			M=11;
		}
		if(m==2){
			Y=Y-1;
			M=12;
		}
		int W =(1 + (int)(2.6*M -0.2)+5*(Y%4)+3*Y+5*(C%4))%7; 
		//就套公式  數字要檢查清楚 公式正確第一關就過了!
		return W;
	}

	//2. 決定天數  
	static int setDays(int y ,int m){
		int d = 0;
		switch(m){
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			d = 31;
			break;
		case 4: case 6: case 9: case 11:
			d = 30;
			break;
		//2.1 判斷平/閏年
		case 2:
			if(leapYear(y)){
				d = 29; 
			}else{
				d = 28;
			}
		}
		return d;
	}
	
	//2.1 判斷平/閏年
	static boolean leapYear(int year){
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
			return true;
		} 
	    return false;
	}

	//3. 印出月曆
	static void printCalendar(int w, int d){
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
					System.out.print("\t");
				}else if(days<10){
					System.out.print("  "+(days++)+"\t");
				}else{
					System.out.print(" "+(days++)+"\t");
				}
			}
			System.out.println();
		}
	}
}  

