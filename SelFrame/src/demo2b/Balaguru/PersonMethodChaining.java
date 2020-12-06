package demo2b.Balaguru;

public class PersonMethodChaining {
	private String name;
	private int age;

	// In addition to having the side-effect of setting the attributes in question,
	// the setters return "this" (the current Person object) to allow for further chained method calls.

	public PersonMethodChaining setName(String name) {
	    this.name = name;
	    return this;
	}

	public PersonMethodChaining setAge(int age) {
	    this.age = age;
	    return this;
	}

	public void introduce() {
	    System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
	}
	public static void main(String[] args) {
		PersonMethodChaining person = new PersonMethodChaining();
	    // Output: Hello, my name is Peter and I am 21 years old.
	    person.setName("Peter").setAge(21).introduce();
	    person.setName("Kalyan").setAge(38).introduce();
		person.setName("Smith");
		person.setAge(21);
		person.introduce();
	}

}
