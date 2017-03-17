package tw.org.iii.ed;

/*月曆的形狀就是一個標準的二維陣列[第N周][星期N]
 * 應該是可以用二維陣列印出    
 * 昨天lab的時候  想到這個問題  所以就動手寫看看
 * 這個月曆只是列印基本模型   
 * 再配上   1.計算天數    2.計算每月第一天星期幾    就可以印出萬年曆了
 * 提供給大家參考!
*/

public class CalendarModel {  
	
	public static void main(String[] args){
		//一個月最多會有6周  所以先創造一個二維陣列[周][天]
		int[][] calendar = new int[6][7];
		
		//創一個一維陣列  總數要=6*7  等等要把這邊的結果塞到上面的二維
		int[] setCalendar = new int[42];
		
		//先找出本月份的第一天是星期幾  然後從那天開始做記號   有算到的日期就印上"1"  
		//這邊以2017年3月為例  第一天是禮拜3 然後有30天
		for(int i=0;i<30;i++){
			setCalendar[3+i]=1;
		}
		
		//把剛剛有做記號的塞到一開始創的二維陣列
		for(int i=0; i<setCalendar.length; i++){
			calendar[i/7][i%7]=setCalendar[i];
			//這邊是用到撲克牌的攤牌原理
		}
		
		//到這邊可以先sysout一下  看看成果
		/*for(int i=0; i<6;i++){
		 * 		for (int j=0;j<7;j++){
		 * 			System.out.print(calendar[i][j]);
		 * 		}
		 * 		System.out.println();
		 * }
		 * 沒意外的話  會看到一個月曆的雛形    不過都是0和1
		*/
		
		//接下來就是把0和1換掉  先宣告一個days 從1號開始
		int days = 1;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(calendar[i][j] == 0){
					//如果陣列裡面是0  就不印
					System.out.print("\t");
				}else{
					//如果不是0 就印 每印一次就++
					System.out.print((days++)+"\t");
				}
			}
			//換行
			System.out.println();
		}
		//月曆出爐!
	} 
}  

