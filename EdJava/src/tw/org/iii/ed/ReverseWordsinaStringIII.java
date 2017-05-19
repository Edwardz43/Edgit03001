package tw.org.iii.ed;

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
		String s = "Let's take LeetCode contest";
		StringBuffer temp = new StringBuffer();
		temp.append(s.substring(0,s.length()));
		temp.reverse();
		StringBuffer result = new StringBuffer();
		while(temp.indexOf(" ")>0){
			result.insert(0, temp , 0, temp.indexOf(" "));
			result.insert(0," ");
			temp.delete(0, temp.indexOf(" ")+1);
		}
		result.insert(0, temp , 0, temp.length());	
		
			
		System.out.println(temp);
		System.out.println(result);
		
	}
}

