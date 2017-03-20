package tw.org.iii.ed;

public class MacthesPractice {  
	static boolean checkPhoneNumber(String number){
		if(!number.matches("^[0][2-9][-]?[0-9]{7,8}$")){
			return false;
		}
		return true;
	} 
	static boolean checkCellPhoneNumber(String number){
		if(!number.matches("^[0][9][0-9]{2}[-]?[0-9]{3}[-]?[0-9]{3}$")){
			return false;
		}
		return true;
	}
	static boolean checkDate(String number){
		if(number.matches("^[\\d]{1,4}[/]?([0]?[1-9]|[1][0-2])[/]?([0-2]?[1-9]|[3][01]|[1][0]|[2][0])$")){
			return true;
		}
		return false;
	}
	static boolean checkTime(String number){
		if(number.matches("^([01]?[0-9]|[2][0-3])(:[0-5]?[0-9]){2}$")){
			return true;
		}
		return false;
	}
	static boolean checkIpv4(String number){
		
		if(number.matches("^(([2][0-4][0-9]|[2][5][0-5]|[01]?[0-9]?[0-9])[.]){3}([2][0-4][\\d]|[2][5][0-5]|[01]?[\\d][\\d]?)$")){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		System.out.println(checkDate("1894/9/1"));
		System.out.println(checkDate("102/09/01"));
		System.out.println(checkPhoneNumber("02-27693495"));
		System.out.println(checkPhoneNumber("073345678"));
		System.out.println(checkCellPhoneNumber("0912116487"));
		System.out.println(checkCellPhoneNumber("0912-116-487"));
		System.out.println(checkTime("01:02:03"));
		System.out.println(checkTime("23:59:59"));
		System.out.println(checkIpv4("192.168.1.1"));
		System.out.println(checkIpv4("2.8.1.1"));
	}
}  

