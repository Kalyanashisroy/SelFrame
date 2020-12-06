package demo2b.Balaguru;

import java.io.DataInputStream;
import java.io.IOException;

public class Reading50 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DataInputStream dis=new DataInputStream(System.in);
		int intNumber=0;
		float floatNumber=0.0f;
		
		try {
			System.out.println("Enter an interger: ");
			intNumber =Integer.parseInt(dis.readLine());
			System.out.println("Enter a float number: ");
			floatNumber=Float.valueOf(dis.readLine()).floatValue();
			
		} catch (Exception e) {
			
		} 
		System.out.println("intNumber = "+intNumber);
		System.out.println("floatNumber = "+floatNumber);

	}

}
