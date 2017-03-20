package tw.org.iii.ed;

import javax.swing.JOptionPane;

public class Hw313_v2 {
	/*
	 * 3/15 修改BUG :
	 * 第一次輸入錯誤  再輸入正確密碼 
	 * 但密碼卻停在第一次沒有儲存第二次正確的密碼
	 * 3/16 加入try&catch 解決JOP點到取消的狀況(感謝仲威指導!)
	 * 3/20 改用matches檢查輸入
	*/
	
	static String setPw(){ //改成回傳String
		// 1-1 測試輸入正確? 
		//    預設3次  失敗就結束程式
		for(int j =0;j<3;j++){
			//1-1-1檢查輸入字元正確? 
			String password =JOptionPane.showInputDialog("請輸入密碼:\n(6~20碼,字元限制a-z, A-Z, 0-9, 或$_*%?^等)");
			if(password.matches("^[\\w$%^*_?]{6,20}$")){  //用限制的字元去檢查輸入的密碼  只要通過測試 checkChar就會++
				return password;//輸入正確 就回傳儲存的正確密碼
			}else{
				JOptionPane.showMessageDialog(null,"輸入錯誤!\n請輸入6~20碼,字元限制a-z, A-Z, 0-9, 或$_*%?^等");//輸入錯誤 重來(3次機會)
			}
		}
		return "ban";//多加一個回傳值   如果3次都錯誤  就回傳ban 等等會用到這個
	}
	
	static boolean checkPw(String password){
		//2-1 設定失敗次數  預設3次
		String checkPw="";
		int hist=0;
		for(int i=0;i<3;i++){
			
			//2-2 檢查密碼 
			checkPw = JOptionPane.showInputDialog("確認密碼,請再次輸入:\n剩餘輸入次數:"+(3-hist));
			hist ++;  
			if(checkPw.equals(password)){
				return true; //正確 => 回傳 true
			}
		}
		return false;  //失敗3次  => 回傳false
	}
	
	public static void main(String[] args) {
		try{
			// Hw: password , 6~20, a-z A-Z 0-9 $_*%?^
			// 1. 輸入密碼
			String password ="";
			password = setPw();
			
			//多加一個判斷  如果回傳的不是ban 就繼續下一個動作  若回傳ban  表示輸入3次皆失敗  程式直接結束
			if(password!="ban"){
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
			
		//當JOP按到取消  就終止程式	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "【系統即將終止】\n請稍後重試!");
		}
	}
}
