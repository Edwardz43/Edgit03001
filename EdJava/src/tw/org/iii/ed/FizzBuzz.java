package tw.org.iii.ed;

import java.util.ArrayList;
import java.util.List;

/*
Write a program that outputs the string representation of numbers from 1 to n.
But for multiples of three it should output “Fizz” instead of the number 
and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

	public static void main(String[] args) {
		List<String> test = fizzBuzz(20);
		for(String t : test){
			System.out.print(t+",");
		}
	}
	
	
    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for(int i =1; i <= n; i++){
        	String temp = "";
        	if(i % 3 == 0) temp += "Fizz";
        	if(i % 5 == 0) temp += "Buzz";
        	if(temp=="") res.add(""+i);
        	else res.add(temp.toString());
        }
        return res;
    }
	    
	
}

