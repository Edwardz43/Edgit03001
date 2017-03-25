package tw.org.iii.ed;

public class Calendar_v3 {  
	//物件導向實作  可以new出calendar
	Calendar_v3(int y){
		System.out.println("列印年份: "+y);		
		for(int i=1;i<13;i++){
			int w = week(y,i,1);
			int d =setDays(y,i);
			System.out.println(i+"月");
			String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
			for(String v :week){
				System.out.print(v+"\t");
			}
			System.out.println();
			printCalendar(w,d);
			System.out.println();
		}
	}
	
	Calendar_v3(int y, int m){
		
		
		int w = week(y,m,1);
		int d =setDays(y,m);
		
		System.out.println("列印月份: "+y+"年"+m+"月");
		String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(String v :week){
			System.out.print(v+"\t");
		}
		System.out.println();
		printCalendar(w,d);	
	}
	
	Calendar_v3(int y, int m, int d){
		
		String week = "日一二三四五六";
		int date = week(y, m, d);
		System.out.printf("%d年%d月%d日 : 星期%s",y,m,d,week.substring(date, date+1));
	}
	
	static int week(int y, int m, int d){
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
		int W =(d + (int)(2.6*M -0.2)+5*(Y%4)+3*Y+5*(C%4))%7; 
		return W;
	}
	
	static int setDays(int y ,int m){
		int d = 0;
		switch(m){
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			d = 31;
			break;
		case 4: case 6: case 9: case 11:
			d = 30;
			break;
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
	
	void printCalendar(int w, int d){
		
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

