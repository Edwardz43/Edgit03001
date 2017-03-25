package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Class312_04 {
	public static void main(String[] args) {
		int n = 3;
		//1. Create Answer
		String answer = createAnswer(n).toString();
		System.out.println(answer);
		
		//2. Start
		boolean isWinner = false;
		for(int i=0; i<5; i++){
			//2-1. input a number string
			String guess = JOptionPane.showInputDialog("猜"+n+"個數字:");
			//2-1.1 regex check input
			if(guess.matches("^[\\d]*$")&&guess.length()==n){
				
				//2-2. compare string =>result
				StringBuffer result = new StringBuffer().append(checkAB(answer , guess));
				System.out.println(result);
				JOptionPane.showMessageDialog(null, result);
				if(result.toString().equals(n+"A0B")){
					//2-3. winner => break
					isWinner = true;
					break;
				}
				//2-4. repeat 10 times => loser
			
			}else{
				JOptionPane.showMessageDialog(null, "輸入錯誤!");
			}
		}
		// show ......../.........
		if(isWinner){
			JOptionPane.showMessageDialog(null, "Winner !");
		}else{
			JOptionPane.showMessageDialog(null, "Lose...,Answer is "+answer);
		}
	}
	// method: checkAB
	static String checkAB(String a, String g){
		int A, B; A = B =0;
		for (int i =0 ; i < g.length(); i++){
			if(g.charAt(i) == a.charAt(i) ){
				A++;
			}else if(a.indexOf(g.charAt(i))!=-1){
				B++;
			}
		}
		return A + "A" + B + "B";
	}
	
	// method: create answer
	static StringBuffer createAnswer(int number){
		boolean[] check = new boolean[10];
		int[] poker = new int[number];
		int temp;
		for(int i=0;i<poker.length;i++){
			do{
				temp = (int)(Math.random()*10);
			}while(check[temp]);
			
			poker[i] = temp;
			check[temp]=true;
		}
		StringBuffer ret = new StringBuffer();
		for(int v :poker) ret = ret.append(v);
		return ret;
	} 
}
