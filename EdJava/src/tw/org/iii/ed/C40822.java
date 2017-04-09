package tw.org.iii.ed;

import java.util.HashMap;
import java.util.Set;

public class C40822 {

	public static void main(String[] args) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "Brad");
		map.put("weight", 80.5);
		map.put("gender", true);
		Set<String> keys = map.keySet();
		// method 1
		for(String key: keys){
			System.out.println(key + "=>"+map.get(key));
		}
		
		// method 2
		for(Object obj : map.entrySet()){
			System.out.println(obj);
		}
	}
}
