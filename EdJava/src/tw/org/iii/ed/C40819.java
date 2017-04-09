package tw.org.iii.ed;

import java.util.HashSet;

public class C40819 {
	public static void main(String[] args){
		HashSet set = new HashSet();
		
		int i1 = 12;
		boolean t = true;
		char c = 's';
		set.add(i1);
		set.add(t);
		set.add(c);
		
		Object obj1 = new Object();
		System.out.println(obj1);
		System.out.println(set);
		System.out.println("---------------");
		
		for(Object obj : set){
			System.out.print(obj+",");
		}
	}
}
