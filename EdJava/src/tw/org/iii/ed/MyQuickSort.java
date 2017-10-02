package tw.org.iii.ed;

import java.util.ArrayList;
import java.util.List;

public class MyQuickSort {
	
	public MyQuickSort() {}
	
	public static void sort(int[] array) {
		List<Integer> list = new ArrayList<>();	
		for(int i : array) {
			list.add(i);	
		}
		long start = System.currentTimeMillis();
		list = sort(list);
		System.out.println("QS : "+(System.currentTimeMillis() - start));
		for(int i = 0; i < array.length;++i) {
			array[i] = list.get(i);
		}
	} 
	
	public static List<Integer> sort(List<Integer> list){
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		if(list.size() < 2) {
			return list;
		}
		int pivot = list.remove(list.size()/2);
		for(int i : list) {
			if(i > pivot) {
				left.add(i);
			}else{
				right.add(i);
			}
		}
		result.addAll(sort(left));
		result.add(pivot);
		result.addAll(sort(right)); 
		return result;
	}

}
