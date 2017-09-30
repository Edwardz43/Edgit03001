package tw.org.iii.ed;

public class JudgeRouteCircle {
	public static boolean getResult(String moves) {
		int x, y;
		x = y = 0;
		char[] chars = moves.toCharArray();
		for(char c : chars) {
			switch(c){
				case 'U':
					y++;
					break;
				case 'D':
					y--;
					break;
				case 'L':
					x--;
					break;
				case 'R':
					x++;
					break;
			}
		}
		if(x == 0 && y == 0) {
			return true;
		}else {
			return false;
		}
		
	} 
	
	public static void main(String[] args) {
		String str = "UDUULLRRLRDDLR";
		System.out.println(getResult(str));
	}

}
