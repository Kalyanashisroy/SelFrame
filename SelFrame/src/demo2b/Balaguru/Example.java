package demo2b.Balaguru;

public class Example {
	public static void main(String[] args) {
		new MethodChaining().setint(20).setfloat(40).display();
	}
}
class MethodChaining{
	private int a; 
    private float b; 
     MethodChaining() 
    { 
        System.out.println("Calling The Constructor"); 
    } 
     public MethodChaining setint(int a) 
    { 
        this.a = a; 
        return this; 
    } 
    public MethodChaining setfloat(float b) 
    { 
        this.b = b; 
        return this; 
    } 
    void display() 
    { 
        System.out.println("Display="
                           + a + " " + b); 
    } 
}
