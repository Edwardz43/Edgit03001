package tw.org.iii.ed;

import java.util.HashSet;
import java.util.TreeSet;

public class C40820 {

	public static void main(String[] args) {
		HashSet<String> set =new HashSet<>();
		set.add("1");
		HashSet set2 = new HashSet();
		set2.add("2");
		set2.add("3");
		set2.add("4");
		HashSet set3 = new HashSet();
		set3.add("5");
		set3.add("6");
		set3.add("7");
		set2.addAll(set3);
		set.addAll(set2);
		System.out.println(set);
	}
}
