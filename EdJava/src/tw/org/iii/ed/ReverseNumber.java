package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class ReverseNumber {  
	static Long getReverse(Long x){
		Long result =x%10;
		Long res = x/10;
		do{
			result = result*10 + res%10;
			res = res/10;
			if(result - x < Integer.MIN_VALUE|| result- x > Integer.MAX_VALUE){
				return 0l;
			}
		}while(res !=0);
		return result;
	}

	public static void main(String[] args){
		long input = Long.parseLong(JOptionPane.showInputDialog("Enter number :"));
		JOptionPane.showMessageDialog(null, "Your enter number :"+ input+
				"\nResult :"+getReverse(input));
	}
}  


