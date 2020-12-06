package demo2b.Balaguru;

public class Patterns {

	private static int i;

	public static void main(String[] args) {
		int n = 5; 
        //pyramidPattern(n);
		//rightTriangle(n);
		printStars(n);
      		
	}
	public static void pyramidPattern(int n) 
    {  
        for (int i=0; i<n; i++) //outer loop for number of rows(n) 
        { 
        	for (int j=n-i; j>1; j--) //inner loop for spaces
          	{ 
                System.out.print(" "); //print space
            }  
        	for (int j=0; j<=i; j++ ) //inner loop for number of columns
            { 
                System.out.print("* "); //print star
            } 
            System.out.println(); //ending line after each row
        }  
    } 
	public static void rightTriangle(int n) 
    { 
        int i, j;  
        for(i=0; i<n; i++) //outer loop for number of rows(n) 
        { for(j=2*(n-i); j>=0; j--) // inner loop for spaces 
            {           
                System.out.print(" "); // printing space
            } 
            for(j=0; j<=i; j++) //  inner loop for columns
            {       
                System.out.print("* "); // print star
            }           
            System.out.println(); // ending line after each row
        } 
    } 
	public static void printStars(int n) 
    { 
        int i, j;  
        for(i=0; i<n; i++) //outer loop for number of rows(n) 
        	{ for(j=2*(n-i); j>=0; j--) // inner loop for spaces 
            {           
                System.out.print(" "); // printing space
            } 
            for(j=0; j<=i; j++) //  inner loop for columns
            {       
                System.out.print("* "); // print star
            }           
            System.out.println(); // ending line after each row
        } 
    } 
    
}

