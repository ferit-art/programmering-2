package upp1;

public class Student extends Person {
	private int grade;
	private String education;

	public Student(int grade, String education, String name, int age) {
		super(name, age);

		this.grade = grade;
		this.education = education;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}

	public void printInfo() {
		super.printInfo();
		System.out.println(getName() + "(" + getAge() + ")s grade in " + education + " is " + grade);
	}
}
