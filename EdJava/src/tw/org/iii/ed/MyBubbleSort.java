package tw.org.iii.ed;

public class MyBubbleSort {
	public static void bubbleSort(int[] array) {
		long start = System.currentTimeMillis();
		int len = array.length;
		int k = 0;
		for(int m = len; m >= 0; m--) {
			for(int i = 0; i < len - 1; i++) {
				k = i + 1;
				if(array[i] > array[k]) {
					swapNum(i, k, array);
				}
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		printNum(array);
	}

	private static void swapNum(int i, int k, int[] array) {
		int tmp = array[i];
		array[i] = array[k];
		array[k] = tmp;
	}
	

	private static void printNum(int[] array) {
		for(int i = 0; i < array.length; i++) {
			//System.out.print(array[i] + ((i == array.length - 1)?"":","));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int n = 50000;
		int[] array = new int[n];
		for(int i = 0; i < n; i++) {
			array[i] = (int)(Math.random()*300 + 1);
		}
		bubbleSort(array);
	}
}
