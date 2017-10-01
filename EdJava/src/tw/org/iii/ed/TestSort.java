package tw.org.iii.ed;

import java.util.ArrayList;
import java.util.List;

public class TestSort {
	private int length;
	private List<Integer> tmpList;
	private int[] array;
	
	public TestSort(int length) {
		this.length = length;
		tmpList = new ArrayList<>();
		for(int i = 0; i < length; i++) {
			tmpList.add((int)(Math.random()*100));
		}
		array = new int[length];
		for(int i = 0; i < array.length; i++) {
			array[i] = tmpList.get(i); 
		}
		
	}
	
	public int[] getList() {
		return this.array;
	}
}
