package tw.org.iii.ed;

public class ReverseInteger {

	public static void main(String[] args) {
		int res = reverse(-1000000003);
		System.out.println(res);
	}
	public static int reverse(int x) {
		int res = 0;
        if(x == 0) return 0;
        try{     
			if(x < 0) {
				StringBuffer temp = new StringBuffer(""+(x* -1));
				res = Integer.parseInt(temp.reverse().toString())*-1;
				return res;
			}else{
				StringBuffer temp = new StringBuffer(""+x);
				res = Integer.parseInt(temp.reverse().toString()); 
				return res;
			}
        }finally{
        	if(x != 0 && res == 0) return 0;
        }
    }
	
}

