public class Transaction {
	
	protected int id;
	protected String customerName;
	protected double total;	
	protected static int transactionCount = 1000;
	protected String destination;
		
	public Transaction () {
		total = 0.0;
		destination = "in";
		id = transactionCount;
		transactionCount++;
	}
	
	public Transaction (String n, double t) {
		total = t;
		customerName = n;
		destination = "in";
		id = transactionCount;
		transactionCount++;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void print() {
		System.out.println("Transaction: " + id);
		System.out.println("Guest: " + customerName);
		System.out.println("Total: $" + total);
	}
	
}
