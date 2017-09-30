package tw.org.iii.ed;

import java.util.ArrayList;

public class KeyboardRow {
	
	public static String[] getResult(String[] words) {
		ArrayList<String> result = new ArrayList<>();
		for(String str : words) {
			if(str.toLowerCase().matches("^[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*$")) {
				result.add(str);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	public static void main(String[] args) {
		String[] input = {"Terry", "Alaska", "Dad", "Peace", "fad", "CNN"};
		String[] output = getResult(input);
		for(String s : output) {
			System.out.println(s);
		}
	}
}
