package tw.org.iii.ed;
public class C32503 {
	public static void main(String[] args) {
		Bird b1 = new Bird();
		int n = -1;
		try{
			b1.setLeg(n);
		}catch(MyException e1){
			System.out.println(e1.toString());
		}catch(MyException2 e2){
			System.out.println(e2.toString());
		}catch(Exception ee){
	
		}
		System.out.println("End");
	}
}
class Bird {
	private int leg;
	//void setLeg(int n) throws MyException, MyException2{
	void setLeg(int n) throws Exception{	
		if(n>2){
			throw new MyException2();
		}else if(n < 0){
			throw new MyException();
		}
		leg = n;
	}
}
class MyException extends Exception{
	@Override
	public String toString(){
		return "哪有欠人家腳的?";
	}
}
class MyException2 extends Exception{
	@Override
	public String toString(){
		return "腳太多?"; 
	}
}