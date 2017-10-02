package tw.org.iii.ed;

public class MySelectionSort {
	
	public static int[] sort(int[] input) {
		long start = System.currentTimeMillis();
		int[] array = input;
		for(int i = 0; i < array.length - 1; i++) {
			//int index = i;
			for(int j = i + 1; j < array.length; j++) {
				if(array[i] > array[j]) {
					swapNum(i, j, array);
				}
			}
		}
		System.out.println("SS : " + (System.currentTimeMillis() - start));
		//printNum(array);
		return array;
	}
	
	private static void swapNum(int i, int j, int[] array) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	private static void printNum(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ((i == array.length - 1)?"":","));
		}
		System.out.println();
	}

}
