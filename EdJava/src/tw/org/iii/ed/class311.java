package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class class311 {
	
	public static void main(String[] args) {
		String input = 
				JOptionPane.showInputDialog("輸入年分:");
		int year = Integer.parseInt(input);
		
		if(year % 4 ==0){
			if(year % 100 == 0){
				if(year % 400 == 0){
					JOptionPane.showMessageDialog(null, "閏年");
				}else{
					JOptionPane.showMessageDialog(null, "平年");
				}
			}else{
				JOptionPane.showMessageDialog(null, "閏年");
			}
		}else
			JOptionPane.showMessageDialog(null, "平年");	
	}
}

