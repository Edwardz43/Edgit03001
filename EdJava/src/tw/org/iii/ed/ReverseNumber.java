package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class ReverseNumber {  
	static Long getReverse(Long n){
		Long result =n%10;
		Long x = n/10;
		do{
			result = result*10 + x%10;
			x = x/10;
			if(result - n < Integer.MIN_VALUE|| result- n > Integer.MAX_VALUE){
				return 0l;
			}
		}while(x !=0);
		return result;
	}

	public static void main(String[] args){
		long input = Long.parseLong(JOptionPane.showInputDialog("Enter number :"));
		JOptionPane.showMessageDialog(null, "Your enter number :"+ input+
				"\nResult :"+getReverse(input));
	}
}  


