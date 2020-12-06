package demo2b.W3Resource;

public class Exercise15 {

	public static void main(String[] args) {
		int a, b;
		a = 15;
		b = 27;
		System.out.println("Before swapping : a, b = "+a+", "+ + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("After swapping : a, b = "+a+", "+ + b);
	}

}
