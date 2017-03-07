package tw.org.iii.ed;

	import java.util.*;

	public class Fib {
	    private List<Integer> fib = new ArrayList<>();
	    {
	        fib.add(0);
	        fib.add(1);
	    }
	    
	    Integer get(int n) {
	        if(n >= fib.size())
	        	for(int i = fib.size(); i <= n; i++) {
	            fib.add(fib.get(i - 1) + fib.get(i - 2));
	        	}
	        return fib.get(n);
	    }
	    
	    public static void main(String[] args) {
	    	System.out.println("Enter Number :");
			
			Scanner scan = new Scanner(System.in);
			
			int num = scan.nextInt();
			
	    	Fib fibonacci = new Fib();
	    	
	        for(int i = 0; i < num; i++) {
	            System.out.print(fibonacci.get(i) + " ");
	        }
	    }
}

