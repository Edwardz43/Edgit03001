package tw.org.iii.ed;
/*
 *   多型實作練習: 萬年曆
 *   抽象類別: Calendar
 *   用三種型式: year, month, day來實作
 */
public  class Calendar_v4 {  
	public static void main(String[] args){
		myCalendar c1 = new yearCalendar(2017);
		myCalendar c2 = new monthCalendar(2017,3);
		myCalendar c3 = new dayCalendar(2017,3,21);
		c2.printCalendar();		
	}
}

interface myCalendar{
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
	void printCalendar();
}


class yearCalendar implements myCalendar{
	private int y;
	public yearCalendar(int y) {
		this.y=y;
	}
	public void printCalendar(){
		System.out.println("列印年份: "+y);
		for(int k=1;k<13;k++){
			int w = myCalendar.week(y,k,1);
			int d =myCalendar.setDays(y,k);
			System.out.println(k+"月");
			String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
			for(String v :week){
				System.out.print(v+"\t");
			}
			System.out.println();
			int[][] myCalendar = new int[6][7];
			int[] setCalendar = new int[42];
			for(int i=0;i<d;i++){
				setCalendar[w+i]=1;
			}
			
			for(int i=0; i<setCalendar.length; i++){
				myCalendar[i/7][i%7]=setCalendar[i];
			}
			
			int days = 1;
			for(int i=0;i<6;i++){
				for(int j=0;j<7;j++){
					if(myCalendar[i][j] == 0){
						System.out.print("\t");
					}else if(days<10){
						System.out.print("  "+(days++)+"\t");
					}else{
						System.out.print(" "+(days++)+"\t");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}	
}

class monthCalendar implements myCalendar{
	private int y, m;
	public monthCalendar(int y, int m) {
		this.y=y; this.m=m;
	}
	public void printCalendar(){
		int w = myCalendar.week(y,m,1);
		int d =myCalendar.setDays(y,m);
		
		System.out.println("列印月份: "+y+"年"+m+"月");
		String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(String v :week){
			System.out.print(v+"\t");
		}
		System.out.println();
		int[][] myCalendar = new int[6][7];
		int[] setCalendar = new int[42];
		for(int i=0;i<d;i++){
			setCalendar[w+i]=1;
		}
		
		for(int i=0; i<setCalendar.length; i++){
			myCalendar[i/7][i%7]=setCalendar[i];
		}
		
		int days = 1;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(myCalendar[i][j] == 0){
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

class dayCalendar implements myCalendar{
	private int y, m, d;
	public dayCalendar(int y, int m, int d) {
		this.y=y; this.m=m; this.d=d;
	}
	public void printCalendar(){
		String week = "日一二三四五六";
		int date = myCalendar.week(y, m, d);
		System.out.printf("%d年%d月%d日 : 星期%s",y,m,d,week.substring(date, date+1));
	}
}
