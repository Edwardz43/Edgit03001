package tw.org.iii.ed;

public class C40801 {

	public static void main(String[] args) {
		int []x = {1,2,3};
		int y[] = {4,5,6};
		new C40801().go(x,y);
	}
	void go(int []...z){
		//z => int[][]=>{{1,2,3},{4,5,6}}
		for(int a[]:z)System.out.print(a[0]);
	}

}
