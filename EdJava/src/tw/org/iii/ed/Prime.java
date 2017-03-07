package tw.org.iii.ed;
import java.util.*;

public class Prime {
	
    public static List<Integer> create(int max) {
        LinkedList<Integer> primes = new LinkedList<>();//why linkedlist?
        primes.add(2); primes.add(3); primes.add(5);
        //預設2 3 5為質數
        
        for(int i = 1; i <= (max - 5) / 6; i++) {
            primes.add(6 * i + 1); primes.add(6 * i + 5);
        }
        //收集嫌疑犯
        
        if(primes.getLast() + 2 <= max) {
            primes.add(primes.getLast() + 2);
        }

        for(int i = 2; i < primes.size(); i++) {
            Integer p = primes.get(i);
            for(int j = 2; j * p <= primes.getLast(); j++) {
                primes.remove(Integer.valueOf(j * p));
            }
        }
        return primes;
    }
    
    
    public static void main(String[] args) {
        for(Integer p : create(1000)) {
            System.out.printf("%4d", p);
        }
    }
}
