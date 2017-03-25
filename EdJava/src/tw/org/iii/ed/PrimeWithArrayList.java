package tw.org.iii.ed;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class PrimeWithArrayList {  	 
	public static void main(String[] args){
		ArrayList<Integer> primelist = new ArrayList<>();
		primelist.add(2);primelist.add(3);primelist.add(5);
		System.out.println("Enter number :");
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt(); 
		for(int i=6; i<input; i++){
			if(isPrime(i)){
				primelist.add(i);
			}
		}
		System.out.println("size :" + primelist.size());
		for(Integer v : primelist){
			System.out.print(v+",");
		}
		System.out.println(primelist.size());
		System.out.println("Max :"+primelist.get(primelist.size()-1));
	}
	static boolean isPrime(int i){
		for(int j=2;j<i;j++){
			if(i%j==0){
				return false;
			}
		}
		return true;
	}	
}  

