package tw.org.iii.ed;

public class Class311_10 {
	public static void main(String[] args) {
		int[][] a;
		int[] b[];
		int c[][];
		a = new int[2][];
		a[0] = new int[3];
		a[1] = new int[4];
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a[i].length;j++){
				System.out.print(a[i][j]+" ");
			}
		System.out.println();
		}
	}
}