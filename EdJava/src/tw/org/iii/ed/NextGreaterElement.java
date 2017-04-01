package tw.org.iii.ed;

import java.util.ArrayList;

public class NextGreaterElement{
	public static void main(String[] args){
		int[] num1 = {4,5,1,2};
		int[] num2 = {1,3,4,2,8,6};
		System.out.println(result(num1, num2));
	}
	static ArrayList result(int[] num1, int[] num2){
			ArrayList temp = new ArrayList();
			for(int i=0; i<num1.length;i++){
				int count= 0;
				for(int j=0; j<num2.length; j++){
					if(num1[i]>num2[j]){
						count++;
					}
				}
				temp.add((count==0)?-1:count);
			}
			return temp;
	}	
	
}

