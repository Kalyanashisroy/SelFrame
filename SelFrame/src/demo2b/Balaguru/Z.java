package demo2b.Balaguru;

public class Z{
	public static void main(String[] args) {
		int var=4;
		A.funA(var);
	}
}
class A {
	public static void funA(int a) {
		String[] str= {"abc"};
		B.funB(str);
		System.out.println("Running funA..........");
	}
}
class B {
	public static void funB(String[] str) {
		BI b=new BI();
		C.funC(b);
		System.out.println("Running funB..........");
	}
}
class C{
	public static void funC(BI bi) {
		System.out.println("Running funC..........");
	}
}
