package demo2b.Balaguru;

public class Displaying56 {

	public static void main(String[] args) {
		Display.verifyDisplay3();
		
		
	}

}
class Display {
	
	public static void verifyDisplay() {
	for(int i=10;i>=1;i--) {
		for(int j=10;j>=i;j--) {
			System.out.print("  ");
			System.out.print(i);
			
		}
		System.out.println("\n");
	}
	}
	public static void verifyDisplay1() {
		for(int i=1;i<=10;i++) {
			for (int j=1;j<=i;j++) {
				System.out.print("  ");
				System.out.print(i);
			}
			System.out.println("\n");
		}
	}
	public static void verifyDisplay2() {
		for(int i=5;i>=1;--i) {
			for (int j=i;j>=1;--j) {
				System.out.print("  ");
				System.out.print("i");
			}
			
			System.out.println("\n");
		}
	}
	public static void verifyDisplay3() {
		for(int i=5;i>=1;--i) {
			for(int j=5;j>i;--j) {
				System.out.print(" ");
			}
			for (int k=1;k<=i;++k) {
				System.out.print(i);
			}
			
			System.out.println("\n");
		}
	}
	
	
	
}
