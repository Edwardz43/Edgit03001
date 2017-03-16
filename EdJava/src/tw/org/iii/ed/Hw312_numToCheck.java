package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Hw312_numToCheck {
	
	//中文的奧妙 用最原始的方法對付它=>手動更新例外
	static String zeroClear(String convert){  
        String[] zero = {"零拾","零佰","零仟"}; 
        String result = convert; 
        
        for (int i = 0;i < zero.length ;i++ )  
        {  
            result = result.replace(zero[i], "零");  
        }  
        result = result.replace("零零零零","");  
        result = result.replace("零零零","零");  
        result = result.replace("零零","零");  
        result = result.replace("零萬","萬");  
        result = result.replace("零圓","圓");
        result = result.replace("億萬","億");
        result = result.replace("零億","億");
        return result;  
    }	
	
	public static void main(String[] args) {
		String in =JOptionPane.showInputDialog("請輸入數字:");
		System.out.println(in);//測試用
		String[] chNumber = {"零","壹","貳","参","肆","伍","陸","柒","捌","玖"};
		String[] decimal = {"圓","拾","佰","仟","萬","拾","佰","仟","億","拾","佰","仟","兆"};
		
		//用字串累加的概念來玩
		String convert = "";
		for(int i=0;i<in.length();i++){
			int d = in.length()-i-1;
			convert +=chNumber[in.charAt(i)-48]+decimal[d];	//因為數字字元的ascii碼是從48開始，所以-48後才會顯示出想要的數字
		}
		String result=zeroClear(convert);
		JOptionPane.showMessageDialog(null, "您輸入的金額為 :\n"+result+"正");
	}

}
