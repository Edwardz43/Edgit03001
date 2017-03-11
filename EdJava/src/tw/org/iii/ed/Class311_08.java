package tw.org.iii.ed;

public class Class311_08 {

	public static void main(String[] args) {
		int[] count= new int[6];
		int times = 1000;
		for(int i=0; i<times; i++){
			int dice = (int)(Math.random()*9);
//			switch(dice){
//				case 6:
//					count[3]++;break;
//				case 7:
//					count[4]++;break;
//				case 8:
//					count[5]++;break;
//				default:
//					count[dice]++;
//			}
			count[(dice>=6)?dice-3:dice]++;
		}
		for(int i=0;i<count.length;i++){
			System.out.println
			(i+1+"點出現:"+count[i]+","+(count[i]*100/times)+"%");
		}
	}

}


