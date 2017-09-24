package tw.org.iii.ed;

public class NumberComplement {

}

class Solution {
    public int findComplement(int num) {
        String str = Integer.toBinaryString(num);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			sb.append(1);
		}
		Integer i = Integer.parseInt(str, 2);
		Integer j = Integer.parseInt(sb.toString(), 2);
        return j^i;
    }
}
