package tw.org.iii.ed;

import java.util.Iterator;

public class IterableString implements Iterable<Character>{
	private String str;
	public IterableString(String str) {
		this.str = str;
	}
	
	@Override
	public Iterator<Character> iterator() {
		return new Iterator<Character>() {
			private int index;
			public boolean hasNext() {
				return index < str.length(); 
			}
			public Character next() {
				return str.charAt(index++);
			}
			public void remove() {
				throw new RuntimeException("Not supported");
			}
			
		};
	}
}
