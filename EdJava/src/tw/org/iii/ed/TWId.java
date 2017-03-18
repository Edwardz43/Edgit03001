package tw.org.iii.ed;

public class TWId extends Object{
	
	static String letters ="ABCDEFGHJKLMNPQRSTUVXYWZIO";
	private String id;
	
	TWId(){
		this((int)(Math.random()*26));
	}
	
	TWId(boolean isFemale){
		this(isFemale,(int)(Math.random()*26));
	}
	
	TWId(int area){
		this((int)(Math.random()*2)==0,area);
	}
	
	TWId(boolean isFemale, int area){
		//super();
		char f0 =letters.charAt(area);
		char f1 = isFemale?'2':'1';	
		String temp = "" + f0 + f1;
		for(int i=0;i<7;i++){
			temp += (int)(Math.random()*10);
		}
		
		for(int i = 0;i<10;i++){
			if(isCheckOK(temp+i)){
				id = temp + i;
				break;
			}
		}
	}
	
	
	TWId(String id){
		this.id = id;
	}
	
	boolean isFemale(){
		boolean female = false;
		char gender = id.charAt(1);
		if(gender =='2'){
			return true;
		}
		return false;
	}

	static boolean isCheckOK(String id){
		if(!id.matches("^[A-Z][12][0-9]{0,8}$")) return false;
		
		char f0 = id.charAt(0);
		int n12 = letters.indexOf(f0) + 10;
		int n1 = n12/10;
		int n2 = n12 % 10;
		
		int n3 = Integer.parseInt((id.substring(1,2)));
		int n4 = Integer.parseInt((id.substring(2,3)));
		int n5 = Integer.parseInt((id.substring(3,4)));
		int n6 = Integer.parseInt((id.substring(4,5)));
		int n7 = Integer.parseInt((id.substring(5,6)));
		int n8 = Integer.parseInt((id.substring(6,7)));
		int n9 = Integer.parseInt((id.substring(7,8)));
		int n10 = Integer.parseInt((id.substring(8,9)));
		int n11 = Integer.parseInt((id.substring(9,10)));
		int sum = n1 + n2*9 + n3*8 + n4*7 + n5*6 + n6*5 + 
				n7*4 + n8*3 + n9*2 + n10 + n11;
	
		
		return sum%10==0;
	}
	
//	static boolean preCheck(String id){
//		boolean result = true;
//		
//		if(id.length()!=10){
//			result = false;
//		}else{
//			if(letters.indexOf(id.charAt(0))== -1){
//				result = false;
//			}else{
//				if(letters.indexOf(1)!=1||letters.indexOf(1)!=2){
//					result = false;
//				}else{
//					for(){
//						if(){
//							
//						}
//					}
//				}
//			}
//		}
//		return result;
//	}
	
	String getId(){
		return id;
	}
}
