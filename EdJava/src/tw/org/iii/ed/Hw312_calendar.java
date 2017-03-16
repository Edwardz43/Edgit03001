package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Hw312_calendar {
	/*	keypoint:
	 * 	利用其中的資料 https://goo.gl/nKAXwX 找出y年m月的第一天
	 *  然後延伸至整個月!
	 *  W=(d + [2.6*m -0.2]+5*(y%4)+3*y+5*(c%4))%7  
	 *  這段公式wiki有解釋 看不懂或不想看就跳過無所謂 直接拿來用!
	 *  c:世紀-1
	 *  y:年分後兩位(1,2月y-1)
	 *  m:月份-2 (1,2月 =>11,12)
	 *  d:日期
	 *  
	 *  3/15 新增方法 setDays 主要是想讓main方法簡單明瞭
	*/
	
	//1. 利用公式找出每月的第一天
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
		int W =(1 + (int)(2.6*M -0.2)+5*(Y%4)+3*Y+5*(C%4))%7; //就套公式  數字要檢查清楚 公式正確第一關就過了!
		return W;
	}

	//2. 決定天數  我用swith + 一個if來判斷2月
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
			if(learyear(y)){
				d = 29; 
			}else{
				d = 28;
			}
		}
		return d;
	}
	
	//2.1 判斷平/閏年
	//禮拜六上的內容  直接COPY來用吧!
	static boolean learyear(int year){
		boolean learyear = true;
		if(year % 4 ==0){
			if(year % 100 == 0){
				if(year % 400 == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		} 
	    return false;
	}
	
	//3. 產生月曆   最麻煩的來了...
	static void result(int w, int d){  //新增一個 result方法   輸入每個月的第一天是禮拜幾(w)以及當月一共有幾天(d)
		//3.1 弄一個陣列  根據你輸入的天數(d) 產生相同數量的陣列  然後for一次把數字(i)塞入
		int[] day = new int[d];
		for(int i=0;i<day.length;i++){
			day[i]=i;
		}
		
		//3.2 超級重點  格式!  首先要決定空幾格
		for(int i=0;i<w;i++){//例如 1982年9月的第一天是禮拜3(w)  所以前就要先空3格  for一下吧
			System.out.print("\t");
		}
		
		//3.3 空完3格  接下來就開始塞1號,2號....but 我"先"輸入第一列喔!
		for(int i=0;i<7-w;i++){
			System.out.print("  "+day[i+1]+"\t");
		}
		System.out.println();
		
		//3.4 第二列以後的就會按照規律  所以寫法跟質數的作業類似   不過要記得扣掉第一列的那幾天
		// 3/15 修改一下輸出的樣式  為了整齊美觀  1~9號輸出時前面空兩格  其他的空一格
 		for(int i=0;i<day.length-7+w;i++){
			System.out.print((day[i]+8-w)<10?"  "+(day[i]+8-w)+"\t":" "+(day[i]+8-w)+"\t");//輸出天數時要多"+1"
			if(i%7 ==6){
				System.out.println();
			}
		}
	}
	
	
	// 經過修改  main方法縮短很多   增加閱讀性
	public static void main(String[] args) {
		// 3/15 修改輸入模式   年分和月分開  更清楚   介面改成 JOP
		
		
		// 3/15 加入一個檢查輸入的機制
		int year=0;
		int month=0;
		boolean check = true;
		
		//檢查輸入年分是否正確
		do{
			String Y = JOptionPane.showInputDialog("請輸入年分:\n(格式:yyyy)");
			if(!Y.matches("[0-9]{1,4}")){
				JOptionPane.showMessageDialog(null, "輸入錯誤!\n請輸入正確格式:yyyy");
				check = false;
			}else{
				year = Integer.parseInt(Y);
				check = true;
			}
		}while(!check);
		
		//檢查輸入月分是否正確  除了數字以外  M還必須在1~12之間
		do{
			String M = JOptionPane.showInputDialog("請輸入月分:\n(格式:mm)");
			if(!M.matches("[0-9]{1,2}")){
				JOptionPane.showMessageDialog(null, "輸入錯誤!\n請輸入正確格式:mm");
				check =false;
			}else{
				month = Integer.parseInt(M);
				if(month>12){
					JOptionPane.showMessageDialog(null, "輸入錯誤!\n請輸入正確格式:mm");
					check = false;
				}else{
					check = true;
				}
			}
		}while(!check);
		
		
		
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
		
		//3. 產生月曆 
		result(w,d);

	}
}
