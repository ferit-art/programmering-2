package upp1o2;

public class Car<T> {
	private T owner;
	private String reg;

	public Car(T owner, String reg) {
		this.owner = owner;
		this.reg = reg;
	}

	public T getOwner() {
		return owner;
	}

	public static void main(String[] args) {
		Car<String> car1 = new Car<String>("Lisa O-son", "REG 123");

		// Funkar också, bara att utan toString(); skulle utskriften inte kunnat;
		// omvandla objektets namn till faktiska name variabeln
		
		Car<Person> car2 = new Car<Person>(new Person("Kalle J-son", "990402-1111"), "PEG 669");
		Car<Person> car3 = new Car<Person>(new Person("Ferit J-son", "1000-1111"), "PEG 999");

		System.out.println(car1.getOwner());
		System.out.println(car2.getOwner());
		System.out.println(car3.getOwner() + " " + car3.reg);
	}
}
