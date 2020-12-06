package demo2b.Balaguru;

import java.util.Arrays;

public class AP {
	public static void main(String[] args) {
		int[] c= BI.sum(args);	
		
	}
}
class BI {
	public static int[] sum(String [] args) {
		String[] results= {"9","8"};
		int[] intarray=new int[results.length];
		int intsum=0;
		for(int i=0;i<results.length;i++) {
			intarray[i]=Integer.parseInt(results[i]);
			intsum +=intarray[i];
		}
		System.out.println("Sum of integer is:"+intsum);
		return intarray;
		
		/*int[] intarray=new int[args.length];
		for(int i=0;i<args.length;i++) {
			intarray[i]=Integer.parseInt(args[i]);
		}
		return intarray;*/
		
	}
}

