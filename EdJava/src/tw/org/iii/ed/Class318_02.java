package tw.org.iii.ed;

public class Class318_02 {

	public static void main(String[] args) {
		TWId id1 = new TWId();
		TWId id2 = new TWId(5);
		TWId id3 = new TWId(false);
		TWId id4 = new TWId(true, 12);
		
		System.out.println(id1.getId());
		System.out.println(id2.getId());
		System.out.println(id3.getId());
		System.out.println(id4.getId());
//		TWId id1 = new TWId("A123456789");
//		System.out.println(id1.getId());
//		if(id1.isMale()){
//			System.out.println("Male");
//		}else{
//			System.out.println("Female");
//		}
	}

}
