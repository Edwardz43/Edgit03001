package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Hw312_numToCheck {

	//中文的奧妙 有時零要省略  有時又不能省
	//所以用最原始的方法對付它=>手動更新例外
	static String setZero(String convert){//輸入字串拼貼的結果  然後抓出不合中文語法的  
        String[] zero = {"零拾","零佰","零仟"}; 
        
        for (int i = 0;i < zero.length ;i++ )  
        {  
        	convert = convert.replace(zero[i], "零");//replace 顧名思義 就是取代  
        }
        //還有很多狀況  要自己跑測試來抓
        convert = convert.replace("零零零零","");  
        convert = convert.replace("零零零","零");  
        convert = convert.replace("零零","零");  
        convert = convert.replace("零萬","萬");  
        convert = convert.replace("零圓","圓");
        convert = convert.replace("億萬","億零");
        convert = convert.replace("零億","億");
        return convert;  
    }	
	
	public static void main(String[] args) {
		try{
			String in =JOptionPane.showInputDialog("請輸入金額:");
			System.out.println(in);//測試用
			
			//先建立字串拼貼需要的素材
			String[] chNumber = {"零","壹","貳","参","肆","伍","陸","柒","捌","玖"};
			String[] decimal = {"圓","拾","佰","仟","萬","拾","佰","仟","億","拾","佰","仟"
							    ,"兆","拾","佰","仟"};
			
			//用字串累加的概念來玩  先建立一個空的字串
			String convert = "";
			
			//把輸入的字串(in)轉成中文字
			for(int i=0;i<in.length();i++){
				int d = in.length()-i-1;
				convert +=chNumber[in.codePointAt(i)-48]+decimal[d];	
			/*將char手動轉成int 當作chNumber的索引值  codePointAt會輸出該字元的Unicode
			 *因為數字字元的Unicode碼是從48開始，所以-48後才會顯示出想要的數字
			 *ps:codePointAt這個api的功能  和猜數字用到的chrarAt功能類似 兩個輸出結果會一樣
			 */
			}
			System.out.println(convert);//測試用
			
			//把拼貼成果丟去檢查
			String result=setZero(convert);
			
			
			//成品出爐!
			JOptionPane.showMessageDialog(null, "您輸入的金額為 :\n"+result+"正");
			
		//輸入數字以外的  就終止程式	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"輸入錯誤,程式即將終止\n請重新執行程式!");
		}
	}
	
}
