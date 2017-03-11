package tw.org.iii.ed;

import java.io.*;
import java.util.Scanner;

public class Coins {

	public static void main(String[] args) {
			System.out.println("請輸入數字: ");
			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();
			compute(num);
	}
	
	private static void compute(int i){	
			int a1 = i / 10;
			int a2 = 137 % 10;
			int a3 = a2 / 5;
			int a4 = a2 % 5;
			int a5 = a1 + a3 + a4;
			System.out.printf("10元%d個,5元%d個,1元%d個,共用掉%d個硬幣",a1, a3, a4, a5);
	}		
				

	}


