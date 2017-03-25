package tw.org.iii.ed;
import java.io.*;
import java.lang.Object;
import java.util.Calendar;

import javax.swing.JOptionPane;

//import java.text.SimpleDateFormat;

public class Calendar_luxury{
	public static void main(String[] args) throws 
		Exception{
			
		int mCalendar[][] = new int[6][7];   /* 宣告月曆陣列 */
		int mWeek=0, mDays=0, mMonth=0, mYear;
		   Calendar rightNow = Calendar.getInstance();  /* 產生 月曆元件 */
		   String mString;
		
		String y = JOptionPane.showInputDialog("Please enter Year: ");   
		mYear =Integer.parseInt(y);
		String m = JOptionPane.showInputDialog("Please enter Month: ");
		mMonth =Integer.parseInt(m)-1;
		System.out.println("");
		
		  
		   	
		   	if ( mMonth == 11 )   /* 計算一個月有幾天 */
		   	rightNow.set(mYear+1,0,0);    
		   	else
		   	rightNow.set(mYear,mMonth+1,1);    
		   	rightNow.add(rightNow.DATE,-1);
		   	mDays = rightNow.get(Calendar.DATE);	    
		   	    
		   for ( int day=0; day< mDays; day++ ) {  
		      rightNow.set(mYear,mMonth,day);  /* 設定 年度,月份(0-11),天數 */     
		      mCalendar[rightNow.get(Calendar.WEEK_OF_MONTH)-1][rightNow.get(Calendar.DAY_OF_WEEK)-1] = 1;	   	      
		   }   
		   
		   for(int[] vs :mCalendar){
			   for(int v:vs){
				   System.out.print(v);
			   }
		   }   
	}  
}
