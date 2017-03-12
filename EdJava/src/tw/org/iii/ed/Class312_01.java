package tw.org.iii.ed;

public class Class312_01 {
	public static void main(String[] args) {
		int[] a = new int[]{0,1,2,3,4,5};
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("-----------------");
		for(int v: a){
			System.out.println(v);
		}
		//exchange 2<->5
		System.out.println("-----------------");
		for(int i=0; i<6;i++){
			int j = (int)(Math.random()*6);
			int temp = a[i];
			a[i] = a[j];
			a[j]=temp;
		}
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("-----------------");
		int[][]b ={{1,2,3,4},{5,6,7},{8,9},{10},{11,12,13}};
		for(int[] v1: b){
			for(int v2 :v1){
				System.out.print(v2+" ");
			}
			System.out.println();
		}
	}
			
}
		


