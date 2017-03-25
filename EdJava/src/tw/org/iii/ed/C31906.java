package tw.org.iii.ed;

public class C31906 {

	public static void main(String[] args) {
		Geo g1 = new Square(5);
		Geo g2 = new Triangle(5, 12, 13, 12);
		Geo g3 = new Circle(6);
		
		System.out.println(getArea(g3));
		System.out.println(getPer(g3));
	}
	static double getArea(Geo g){
		return g.calArea();
	}
	static double getPer(Geo g){
		return g.calPer();
	}
}

interface Geo{
	double calPer();
	double calArea();
}
class Triangle implements Geo{
	private double s1, s2, s3, h1;
	Triangle(double s1,double s2,double s3,double h1){
		this.s1=s1; this.s2=s2;this.s3=s3;this.h1=h1;
	}
	public double calPer(){
		return s1+s2+s3;
	}
	public double calArea(){
		return s1*h1/2;} 
}

class Square implements Geo{
	private double s;
	Square(double s){
		this.s=s;
	}
	public double calPer(){
		return s*4;
	}
	public double calArea(){
		return s*s;}
}

class Circle implements Geo{
	private double r; 
	Circle(double r){
		this.r = r;
	}
	public double calPer(){
		return 2*r*Math.PI;
	}
	public double calArea(){
		return Math.PI*r*r;
	}
}