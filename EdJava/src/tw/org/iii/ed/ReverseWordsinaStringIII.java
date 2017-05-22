package tw.org.iii.ed;

import java.util.LinkedList;

//557. Reverse Words in a String III
//Given a string, you need to reverse the order of characters in each word within a sentence, 
//while still preserving whitespace and initial word order.
//
//Example 1:
//Input: "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"
//Note: In the string, each word is separated by single space and there will not be any extra space in the string.



public class ReverseWordsinaStringIII {

	public static void main(String[] args) {
		String s = "This organization has no public members. You must be a member to see who’s a part of this organization";
		long start = System.currentTimeMillis();
		String[] temp = s.split("\\s");
		StringBuffer temp2 = new StringBuffer();
		String result = new String();
		for(int i = 0; i< temp.length -1; i++){
			temp2.append(temp[i]).reverse();
			result = result.concat(temp2.toString() + " ");
			temp2.delete(0, temp2.length());
		}
		temp2.append(temp[temp.length-1]).reverse();
		result = result.concat(temp2.toString());
		long end = System.currentTimeMillis() - start;
		//return result;
		
		
		//result[0] = result[0].append(temp[0]);
		
		//LinkedList<String> result = new LinkedList();
//		result.add(s);
		
//		for(String s1 : temp){
//			
//		}
		System.out.println(end);
		System.out.println(result);
		
	}
}

