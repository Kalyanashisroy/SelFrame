package demo2b.Balaguru;

public class Debugging58 {

	public static void main(String[] args) {
		scope5();

	}
	public static void display1() {
		int x=123456;
		float f=100.12f;
		System.out.println("Float value is :"+f);
	}
	public static void display2() {
		int y = 5;
		int x = 0;
		if(x>10) {
			y=x;
		}
		System.out.println("Value of y is :"+y);
	}
	public static void calculate3() {
		final float pie=3.14f;
		System.out.println("Value of pie is :"+pie);
	}
	public static void convert4() {
		int i=1245;
		byte b=(byte)i;
		System.out.println("Value of byte variable b= "+b);
	}
	public static void scope5() {
		int m=10;
		{
			int m1=20;
			System.out.println("Value of m1 is :"+m1);
		}
		System.out.println("Value of m is :"+m);
		
	}
}
