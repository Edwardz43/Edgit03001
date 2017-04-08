package tw.org.iii.ed;

public class C40802 {

	public static void main(String[] args) {
		say("A","B","C");
	}
//	static void say(String name){
//		System.out.println("Ya ,"+ name);
//	}
//	static void say(String name1 ,String name2){
//		System.out.println("Ya ,"+name1);
//		System.out.println("Ya ,"+name2);
//	}
	static void say(String ...names){
		for(String name:names)
		System.out.println("Ya ,"+ name);
	}
}
