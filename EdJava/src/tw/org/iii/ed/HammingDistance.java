package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class HammingDistance {  
	static int toBit(int x, int y){
		int [] resultX = new int[31];
		int [] resultY = new int[31];
		int count=0;
		for(int i=30;i>0;i--){
			resultX[i] = x/(int)Math.pow(2, i);
			resultY[i] = y/(int)Math.pow(2, i);
			if (resultX[i]!=resultY[i]){
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args){
		int x = Integer.parseInt(JOptionPane.showInputDialog("Enter X :"));
		int y = Integer.parseInt(JOptionPane.showInputDialog("Enter Y :"));
		JOptionPane.showMessageDialog(null, "Hamming distance is "+toBit(x, y));
	}
}  

