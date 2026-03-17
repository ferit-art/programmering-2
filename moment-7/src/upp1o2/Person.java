package upp1o2;

public class Person {
	private String name;
	private String personNum;

	/** Konstruktor */
	public Person(String name, String personNum) {
		this.name = name;
		this.personNum = personNum;
	}

	/** Åtkomstmetod, set-metod */
	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	/** Åtkomstmetod, get-metod */
	public String getPersonNum() {
		return this.personNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Utan denna metoden hade Car klassen bara skrivit ut själva objektets namn och
	// inte Person.name
	public String toString() {
		return this.name;
	}
}
