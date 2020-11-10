
public class DriveThru extends Transaction {
	
	private String car;
	private int lane;
	
	public DriveThru () {
		total = 0.0;
		lane = -1;
		destination = "out";
		id = transactionCount;
		transactionCount++;
	}
	
	public DriveThru (String n, double t, String c, int l) {
		total = t;
		customerName = n;
		destination = "out";
		car = c;
		lane = l;
		id = transactionCount;
		transactionCount++;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("Car: " + car);
		if (lane > 0) {
		System.out.println("Served in Lane: " + lane);}
		else System.out.println("No lane set.");
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

}
