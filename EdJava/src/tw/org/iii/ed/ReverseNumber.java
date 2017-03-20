package tw.org.iii.ed;

public class ReverseNumber {  
	static int getReverse(Integer n){
		Integer result =n%10;
		Integer x = n/10;
		do{
			result = result*10 + x%10;
			x = x/10;
			if(result - n < Integer.MIN_VALUE|| result- n > Integer.MAX_VALUE){
				return 0;
			}
		}while(x !=0);
		return result;
	}

	public static void main(String[] args){
		System.out.println(getReverse(-48949156));
	}
}  


