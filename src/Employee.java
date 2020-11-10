import java.util.Random;
public class Employee {
	
	Random rand = new Random();
	
	//Declaring vars

	private double wage;
	private String name;
	private String email;
	private int clockIn;
	private boolean isManager;
	
	private static int userID = 111111;
	
	//Default constructors
	
	public Employee () {
		name = "N/A";
		email = "";
		wage = -1.0;
		isManager = false;
		clockIn = userID;
		userIdHelper();
	}
	
	public Employee (String n, String e, double w) {
		name = n;
		email = e;
		wage = w;
		isManager = false;
		clockIn = userID;
		userIdHelper();
	}
	
	public Employee (String n, double w) {
		name = n;
		email = "";
		wage = w;
		isManager = false;
		clockIn = userID;
		userIdHelper();
	}
	
	public Employee (double w) {
		name = "N/A";
		email = "";
		wage = w;
		clockIn = userID;
		userIdHelper();
	}
	
	// Special functions
	
	private void userIdHelper () {
		userID = userID + rand.nextInt(10000);
	}

	public boolean isManager () {
		if (isManager == true)
			return true;
			else return false;
	}
	
	public void printMultiLine() {
		System.out.println("Name: " + this.name);
		System.out.println("Clock-In: " + this.clockIn);
		if (this.wage > 0.0)
		System.out.println("Wage: $" + this.wage + "/hr");
		if (!this.email.isBlank())
			System.out.println("Email: " + this.email);
	}
	
	public void print() {
		System.out.print("(" + this.clockIn + ") ");
		System.out.print(this.name);
		if (this.wage > 0.0)
		System.out.print(", $" + this.wage + "/hr");
		if (!this.email.isBlank())
			System.out.print(", " + this.email);
	}
	
	// Getterz and setterz

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public int getClockIn() {
		return clockIn;
	}
	
 
	
}
