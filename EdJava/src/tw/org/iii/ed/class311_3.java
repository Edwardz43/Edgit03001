package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class class311_3 {

	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("輸入數字:");
		int month = Integer.parseInt(input);
		//switch接受 byte short int char(int內)
		
		switch(month){
			default:
				System.out.println("Error !");	
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				System.out.println("31");
				break;
			case 2:
				System.out.println("28");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("30");
				break;
		}

	}

}
