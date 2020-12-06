package demo2b.Balaguru;

public class CityGuide {

	public static void main(String[] args) {
		char choice;
		System.out.println("Select your choice");
		System.out.println(" M -> Madras");
		System.out.println(" B -> Bombay");
		System.out.println(" C -> Calcutta");
		System.out.flush();
		try {
			switch(choice =(char) System.in.read()) {
			case 'M':
			case 'm':System.out.println("Madras :booklet 5");
			break;
			case 'B':
			case 'b':System.out.println("Bombay :booklet 9");
			break;
			case 'C':
			case 'c':System.out.println("Calcutta :booklet 15");
			break;
			default:System.out.println("Invalid choice(IC)");
			}
		}
		catch(Exception e) {
			System.out.println("I/O error");
		}

	}

}
