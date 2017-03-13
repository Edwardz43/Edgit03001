package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Hw313_v2 {
	
	static boolean setPw(String password){
		String limit = new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789$_*%?^");
		// 1-1 測試輸入正確? 
		//    預設3次  失敗就結束程式
		for(int j =0;j<3;j++){
			
			//1-1-1檢查輸入字元正確?
			int checkChar = 0;
			for(int i=0;i<password.length();i++){
				if(limit.indexOf(password.charAt(i))!=-1){  //用限制的字元去檢查輸入的密碼  只要不符合 checkChar就會++
					checkChar++;
				}
			}
			
			//1-1-2 判斷格式正確?
			//System.out.println(checkChar); debug用的
			if(checkChar == password.length() && password.length()>=6 && password.length()<=20){
				return true;
			}
			password = JOptionPane.showInputDialog("輸入錯誤!\n請輸入6~20碼,字元限制a-z, A-Z, 0-9, 或$_*%?^等");
		}
		return false;
	}
	
	static boolean checkPw(String password){
		//2-1 設定失敗次數  預設3次
		int hist=0;
		for(int i=0;i<3;i++){
			
			//2-2 檢查密碼 
			String checkPw = JOptionPane.showInputDialog("確認密碼,請再次輸入:\n剩餘輸入次數:"+(3-hist));
			hist ++;  
			if(checkPw.equals(password)){
				return true; //正確 => 回傳 true
			}
		}
		return false;  //失敗3次  => 回傳false
	}
	
	public static void main(String[] args) {
		// Hw: password , 6~20, a-z A-Z 0-9 $_*%?^
		// 1. 輸入密碼
		String password= JOptionPane.showInputDialog("請輸入密碼:\n(6~20碼,字元限制a-z, A-Z, 0-9, 或$_*%?^等)");
		boolean result = false;
		result = setPw(password);
		
		if(result){
			// 2.確認密碼
			boolean check = false;
			check = checkPw(password);
			
			// 3.ending 正確/錯誤
			if(check){
				JOptionPane.showMessageDialog(null, "密碼正確,恭喜您設定成功!");
			}else{
				JOptionPane.showMessageDialog(null, "操作終止!若有疑問,請洽服務人員!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "操作終止!若有疑問,請洽服務人員!");
		}
	}
}
