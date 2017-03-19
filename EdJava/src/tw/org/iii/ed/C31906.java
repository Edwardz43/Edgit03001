package tw.org.iii.ed;

public class C31906 {

	public static void main(String[] args) {
		Shape s1 = new Square(5);
		Shape s2 = new Triangle(5, 12, 13, 12);
		System.out.println(C3196(s2));
	}
	static double C3196(Shape c){
		return c.calArea();
	} 
}
interface Shape{
	double calLength();
	double calArea();
}
class Triangle implements Shape{
	private double s1, s2, s3, h1;
	Triangle(double s1,double s2,double s3,double h1){
		this.s1=s1; this.s2=s2;this.s3=s3;this.h1=h1;
	}
	public double calLength(){
		return s1+s2+s3;
	}
	public double calArea(){
		return s1*h1/2;} 
}
class Square implements Shape{
	private double s1;
	Square(double s1){
		this.s1=s1;
	}
	public double calLength(){
		return s1*4;
	}
	public double calArea(){
		return s1*s1;}
}