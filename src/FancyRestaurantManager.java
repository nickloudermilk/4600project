import java.util.*;
public class FancyRestaurantManager {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		//Initializing the databases
		HashMap<Integer, Transaction> transactionDatabase = new HashMap<Integer, Transaction>();
		ArrayList<Employee> employeeDatabase = new ArrayList<Employee>();
		String[] emails = null;
		
		//Creating the "master employee"
		Employee master = new Employee ();
		master.setManager(true);
		employeeDatabase.add(master);
		
		//Creating some sample employees
		Employee example1 = new Employee ("John Doe", "john@hotmail.com", 8.50);
			employeeDatabase.add(example1);
		Employee example2 = new Employee ("Donald Trump", "trump@gmail.com", 8.75);
			employeeDatabase.add(example2);
		Employee example3 = new Employee ("Karen Aguar", 14.00);
			employeeDatabase.add(example3);
		Employee example4 = new Employee ("Joe Biden", "president-elect@whitehouse.gov", 10.50);
			employeeDatabase.add(example4);

		
		int lane = 0;
		
		System.out.println("Welcome to Fancy Restaurant Manager!");
		
		do {
			System.out.println();
			System.out.println("##############################################");
			System.out.println("What would you like to do today?");
			System.out.println("0. Set Drive-Thru Lane");
			System.out.println("1. New Inside Transaction");
			System.out.println("2. New Drive-Thru Transaction");
			System.out.println("3. View Daily Sales Total");
			System.out.println("4. Lookup/Refund Transaction");
			System.out.print("5. Manager Functions  ");

			int menuchoice = keyboard.nextInt();
			System.out.println();
			
///////////////////////////////////////////////// 0. Set Drive-Thru Lane //////////////////////////////////////////////////////////////////////
			
			if (menuchoice == 0) {
				keyboard.nextLine();
				System.out.print("Lane 1/2? ");
				lane = keyboard.nextInt(); //read in number
				System.out.println("Sucessfully changed.");
				System.out.println();

				System.out.println("Press any key followed by ENTER to continue.");
				keyboard.nextLine();
			}
			
//////////////////////////////////////////////// 1/2. New Transaction ///////////////////////////////////////////////////////////////////////
			
			if (menuchoice == 1 || menuchoice == 2) {
				keyboard.nextLine();
				
				//Read in regardless of destination
				System.out.print("What's the guest's name? ");
				String name = keyboard.nextLine();
				
				System.out.print("What's the total? ");
				double total = keyboard.nextDouble();
				
				//Only for inside sales
				if (menuchoice == 1) {
					Transaction t = new Transaction(name, total);
					transactionDatabase.put(t.getId(), t);
					System.out.println();
					t.print();	
					System.out.println("...has been stored. Thank you!");
					System.out.println();
				}
				
				//Only for drive-thru sales
				if (menuchoice == 2) {
					keyboard.nextLine();
					System.out.print("What's the car? ");
					String car = keyboard.nextLine();
					DriveThru t = new DriveThru(name, total, car, lane);
					transactionDatabase.put(t.getId(), t);
					System.out.println();
					t.print();	
					System.out.println("...has been stored. Thank you!");
					System.out.println();
					}

				System.out.println("Press any key followed by ENTER to continue.");
			}
			

//////////////////////////////////////////////////// 3. Sales Total ///////////////////////////////////////////////////////////////////////
			
			if (menuchoice == 3) {
				keyboard.nextLine();
				
				//declare vars 
				Double grandTotal = 0.0;
				Double max = 0.0; 
				
				//Loop through transaction values and pull out totals
				for(Transaction currentTrans : transactionDatabase.values()) {
					Double currentTotal = currentTrans.getTotal();
					
					//Determine grand total while looping
					grandTotal = grandTotal + currentTotal;
					
					// Determine max while looping
					if (currentTotal > max) {
						max = currentTotal;
					}
				}
				
				double chkaverage = grandTotal / transactionDatabase.size();
				
				//Print the results
				System.out.println();
				System.out.println("Today's Sales Total Is: $" + grandTotal);
				System.out.println("Today's Largest Sale Is: $" + max);
				System.out.println("Total Number of Transactions: " + transactionDatabase.size());
				System.out.println("Check Average: $" + chkaverage);
				System.out.println();
				System.out.println("Press any key followed by ENTER to continue.");
			}

//////////////////////////////////////////////////// 4. Lookup Transaction ///////////////////////////////////////////////////////////////
			
			if (menuchoice == 4) {
				keyboard.nextLine();
				System.out.println();
				System.out.print("Enter the guest's name: ");
				String lup = keyboard.nextLine(); //lookup by name
				int counter = 0; // to provide numbered list
				
				//loops through all transactions
				for(Transaction currentTrans : transactionDatabase.values()) {
					String currentName = currentTrans.getCustomerName();
					if (currentName.equalsIgnoreCase(lup)) {
						counter ++;
						System.out.print(counter + ") #" + currentTrans.getId() + " - " + currentTrans.getCustomerName() +  " - $" + currentTrans.getTotal());
						
						//adds drive-thru info if relevant  
						if(currentTrans.getDestination().equals("out")) {
								System.out.print(" (Drive-Thru Car: " + ((DriveThru) currentTrans).getCar() + ")");
								}
						System.out.println();
					};
				}
				
				//if no transactions found
				if (counter == 0) {
					System.out.println("No transactions found.");
				}
				
				// if transactions found, offer to start refund process
				else {
					System.out.print("Would you like to refund one of these? Enter Y/N: ");
					if (keyboard.next().equalsIgnoreCase("y") || keyboard.next().equalsIgnoreCase("yes")) {
						System.out.print("Which transaction number? ");
						int refundId = keyboard.nextInt();
						if (transactionDatabase.containsKey(refundId)) { //verifies the user enteres a valid transaction ID
							Transaction torefund = transactionDatabase.get(refundId); //pulls the correct transaction
							System.out.println("Transaction Refunded. You owe " + torefund.getCustomerName() + " $" + torefund.getTotal());
							transactionDatabase.remove(refundId);
							}
						else System.out.print("Invalid Transaction ID.");
					}
				}
				System.out.println();
				System.out.println("Press any key followed by ENTER to continue.");
			}
			
			
//////////////////////////////////////////////////////// 5. Manager Functions ////////////////////////////////////////////////////////////////////
			
			if (menuchoice == 5) {
				System.out.println("Enter your clock in number: ");
				int pin = keyboard.nextInt();
				System.out.println();
				
				for(int i = 0; i < employeeDatabase.size(); i++){
					Employee a = employeeDatabase.get(i);
					int compare = a.getClockIn();
					if (pin == compare) {
						if(a.isManager()) {
							
							// Manager verification successful 
							
							System.out.println("1. Add Employee");
							System.out.println("2. Give Employee Raise");	
							System.out.println("3. Terminate Employee");	
							System.out.println("4. Generate New / View Existing Employee Email List");
							System.out.println("5. View All Employees");			
				
							int mgrMenu = keyboard.nextInt();
							System.out.println();
							
							if (mgrMenu == 1) { 			// 1. Add employee
								
								int count = 0;
								Scanner key1 = new Scanner(System.in);

								System.out.println("Type employee name (or type \"skip\"): ");
								String newEmployeeName = key1.nextLine();
								if (!newEmployeeName.equalsIgnoreCase("skip")) count++;
								keyboard.nextLine();

								System.out.println("Type employee email (or type \"skip\"): ");
								String newEmployeeEmail = keyboard.nextLine();
								if (!newEmployeeEmail.equalsIgnoreCase("skip")) count++;
								
								System.out.println("Type employee wage (or type \"0\"): ");
								double newEmployeeWage = keyboard.nextDouble();
								if (newEmployeeWage > 0.01) count++;

								if (count==0) {
									System.out.println("You didn't enter anything!");
								}
								else {
									Employee e = new Employee();
									if (!newEmployeeName.equalsIgnoreCase("skip")) e.setName(newEmployeeName);
									if (!newEmployeeEmail.equalsIgnoreCase("skip")) e.setEmail(newEmployeeEmail);
									if (newEmployeeWage > 0.0) e.setWage(newEmployeeWage);
									employeeDatabase.add(e);
									
									System.out.println();
									System.out.println("Employee Added! " + "IMPORTANT: Their clock-in number will be: " + e.getClockIn());
								}

							}
							
							if (mgrMenu == 2) { 			// 2. Give employee raise
								
								System.out.print("Enter the employee's clock-in pin. ");
								int raisePin = keyboard.nextInt();
								keyboard.nextLine();
								for (Employee raiseEmp : employeeDatabase) {
									if (raisePin == raiseEmp.getClockIn()) {
										raiseEmp.printMultiLine();
										System.out.println("Is a manager? " + raiseEmp.isManager());
										System.out.println();
										System.out.print("Change [W]age, [M]anager Status, or [C]ancel? ");
										System.out.println();
										String raiseOption = keyboard.next();
										switch(raiseOption) {
										case "W", "w":
											System.out.print("Enter the new wage: $");
											raiseEmp.setWage(keyboard.nextDouble());
											System.out.println("Success! The new wage is: $" + raiseEmp.getWage());
											break;
										case "M", "m":
											if (raiseEmp.isManager()) {
												raiseEmp.setManager(false);
												System.out.println("Success! No longer a manager.");
											}
											else {
												raiseEmp.setManager(true);
												System.out.println("Success! Now a manager. Congrats!");
											}
											break;
										case "c", "C":
											System.out.println("Nothing to see here.");
											break;
										default:
											System.out.println("Invalid entry.");
											break;
										}
										break;
									}
								}

								
							}
							
							if (mgrMenu == 3) { 			// 3. Terminate Employee
								
								System.out.print("Enter the employee's clock-in pin. ");
								int terminatePin = keyboard.nextInt();
								keyboard.nextLine();
								for (Employee endEmp : employeeDatabase) {
									if (terminatePin == endEmp.getClockIn()) {
										employeeDatabase.remove(endEmp);
										System.out.println(endEmp.getName() + " was terminated.");
										break;
									}
									else
										continue;
								}
								
							}
							
							if (mgrMenu == 4) { 			// 4. Generate Email List
								System.out.println("Would you like to generate a [N]ew list or pull the [E]xisting one?");
								Scanner key2 = new Scanner(System.in);
								String emailChoice = key2.nextLine();
								keyboard.nextLine();
								System.out.println();
								
								if (emailChoice.equalsIgnoreCase("n")) {
									// Generate new list
									emails = new String[employeeDatabase.size()];
									for (int z = 0; z < employeeDatabase.size(); z++) {
										Employee curr = employeeDatabase.get(z);
											emails[z] = curr.getEmail();
									}
								}
									
								if (emailChoice.equalsIgnoreCase("e") || emailChoice.equalsIgnoreCase("n")) { 			
									// Print list
									// Check first it is initialized
									if (emails == null) {
										System.out.print("Error: Please generate a new list first!");
									}
									else {
										for (String email : emails) {
											if (!email.isBlank()) //ignore null values
											System.out.println(email);
										}
									}
								}
								
								else {
									System.out.println("Not a valid choice.");
								}
							}
							
							if (mgrMenu == 5) { 			// 5. Generate employee list
								
								System.out.println();
								for (int z = 1; z < employeeDatabase.size(); z++) {
									Employee curr = employeeDatabase.get(z);
									curr.print();
									System.out.println();
								}
								
							}
							
							break; //ends for-loop once manager is authenticated
							
						}
						// User is not a manager
						else {
							System.out.println("Pin is valid, but you're not a manager.");
							break;
						}
					}
					else {
						continue;
						}
				}

				System.out.println();
				System.out.println("Press any key followed by ENTER to continue.");
				
			}

//////////////////////////////////////////////////// Error + close do/while loop ///////////////////////////////////////////////////////////////

			else { 
			}
			
		} while (!keyboard.next().equals("exit"));

	}
	
}
