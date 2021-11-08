import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class CustomerProfInterface {
    private String fileName;
    private CustomerProfDB database;

    public CustomerProfInterface(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        database = new CustomerProfDB(this.fileName);
    }

    public boolean getUserChoice() throws IOException {
        System.out.println("What is your admin ID?");
        Scanner scan = new Scanner(System.in);
        String id = scan.nextLine(); // input for ID
        
        System.out.println("Using ID " + id);
        
        System.out.println("Here are your choices:\n" +
                "1. Enter a New CustomerProf\n" +
                "2. Delete a customer by Name and adminID\n" +
                "3. Find and display a CustomerProf by name and adminID\n" +
                "4. CustomerProf Modifications\n" +
                "5. Display all profiles\n" +
                "6. Write to database\n" +
                "0. Exit\n");

        String choice = scan.nextLine();
        
        
        
        
        if (choice.equals("1"))
            // enter a new customer profile
            database.insertNewProfile(createNewCustomerProf());
        else if (choice.equals("2"))
            // delete customer profile
            deleteCustomerProf();
        else if (choice.equals("3"))
            // find profile
            findCustomerProf();
        else if (choice.equals("4"))
            // customer prof modifications
            updateCustomerProf();
        else if (choice.equals("5"))
            // display all profiles
            displayAllCustomerProf();
        else if (choice.equals("6"))
            // write to database
            writeToDB();
        if (choice.equals("0"))
        	return false;
        return true;
    }

    public CustomerProf createNewCustomerProf() {
        System.out.println("Please write the admin ID:");
        Scanner scan = new Scanner(System.in);
        String adminID = scan.nextLine();

        System.out.println("Please write the first name:");
        String firstName = scan.nextLine();

        System.out.println("Please write the last name:");
        String lastName = scan.nextLine();

        System.out.println("Please write the address:");
        String address = scan.nextLine();

        System.out.println("Please write the phone number:");
        String phone = scan.nextLine();

        System.out.println("Please write the income (numbers only):");
        float income = Float.parseFloat(scan.nextLine());
        
        System.out.println("Please write the status: 1. Active, 2. Inactive");
        String status = scan.nextLine();
        
        while (!status.equals("1") && !status.equals("2")) {
        	 System.out.println("Please write the status: 1. Active, 2. Inactive");
             status = scan.nextLine();
        }
        
        if (status.equals("1"))
        	status = "Active";
        else
        	status = "Inactive";

        System.out.println("Please write the use: 1. Business, 2. Personal, 3. Both");
        String use = scan.nextLine();
        
        while (!use.equals("1") && !use.equals("2") && !use.equals("3")) {
        	System.out.println("Please write the use: 1. Business, 2. Personal, 3. Both");
            use = scan.nextLine();
       }
       
        if (use.equals("1"))
        	use = "Business";
       	else if (use.equals("2"))
       		use = "Personal";
       	else
       		use = "Both";
        
        VehicleInfo vehicle = createNewVehicleInfo();
        

        return new CustomerProf(adminID, firstName, lastName, address, phone, income, status, use, vehicle);

    }

    public VehicleInfo createNewVehicleInfo() {
        System.out.println("Please write the model:");
        Scanner scan = new Scanner(System.in);
        String model = scan.nextLine();
        
        System.out.println("Please write the year:");
        String year = scan.nextLine();

        System.out.println("Please write the type of vehicle: 1. Sedan, 2. Hatchback, 3. Luxury, 4. Sport, 5. Other");
        String type = scan.nextLine();
        
        while (!type.equals("1") && !type.equals("2") && !type.equals("3") && !type.equals("4") && !type.equals("5")) {
        	System.out.println("Please write the type of vehicle: 1. Sedan, 2. Hatchback, 3. Luxury, 4. Sport, 5. Other");
            type = scan.nextLine();
       }
        
        if (type.equals("1"))
        	type = "Sedan";
        else if (type.equals("2"))
        	type = "Hatchback";
        else if (type.equals("3"))
        	type = "Luxury";
        else if (type.equals("4"))
        	type = "Sport";
        else if (type.equals("5"))
        	type = "Other";


        System.out.println("Please write the method of obtaining: 1. New, 2. Certified Pre-owned, 3. Used, 4. Other");
        String method = scan.nextLine();
        
        while (!method.equals("1") && !method.equals("2") && !method.equals("3") && !method.equals("4")) {
        	System.out.println("Please write the method of obtaining: 1. New, 2. Certified Pre-owned, 3. Used, 4. Other");
            method = scan.nextLine();
       }
        
        if (method.equals("1"))
        	method = "New";
        else if (method.equals("2"))
        	method = "Certified Pre-owned";
        else if (method.equals("3"))
        	method = "Used";
        else if (method.equals("4"))
        	method = "Other";
        
        

        return new VehicleInfo(model, year, type, method);
    }

    public void deleteCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();
        
        

        boolean success = database.deleteProfile(adminID, lastName);
        
        
        if (success)
            System.out.println("Successfully deleted profile.");
        else
            System.out.println("Could not delete profile.");
    }

    public void findCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();
        
        

        CustomerProf found = database.findProfile(adminID, lastName);
        if (found == null)
            System.out.println("Could not find profile.");
        else
            displayCustomerProf(found);
    }

    public void displayCustomerProf(CustomerProf input) {
        System.out.printf("Name: %s %s\nAddress: %s\nPhone Number: %s\nStatus: %s\nUse: %s\nIncome: %f\n",
                input.getFirstName(), input.getLastName(), input.getAddress(), input.getPhone(), input.getStatus(), input.getUse(), input.getIncome());
        VehicleInfo vehicle = input.getVehicleInfo();
        System.out.printf("Vehicle Model: %s\nVehicle Type: %s\nMethod of obtaining: %s\nYear of production: %s\n\n",
                vehicle.getModel(), vehicle.getType(), vehicle.getMethod(), vehicle.getYear());
    }

    public void updateCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();

        CustomerProf update = database.findProfile(adminID, lastName);
        
        if (update == null)
        	System.out.println("Could not find profile.");
        else {
        	System.out.printf("Modifying %s %s: \n", update.getFirstName(), update.getLastName());
	        System.out.println("Please select which one to modify:\n" +
	                "1. Address\n" +
	                "2. Phone\n" +
	                "3. Use\n" +
	                "4. Status\n" +
	                "5. Vehicle Model\n" +
	                "6. Vehicle Year\n" +
	                "7. Vehicle Type\n" +
	                "8. Vehicle Method of Obtaining\n");
	        String choice = scan.nextLine();
	        
            if (choice.equals("1")) {
                System.out.println("Enter new address:");
                update.updateAddress(scan.nextLine());
            }
            else if (choice.equals("2")) {
                System.out.println("Enter new phone number:");
                update.updatePhone(scan.nextLine());
            }
            else if (choice.equals("3")) {
            	
            	System.out.println(
                		"Enter new use:\n" +
                		"1. Business\n" +
                		"2. Personal\n" +
                		"3. Both\n");
                
                String newUse = scan.nextLine();
                
                while (!newUse.equals("1") && !newUse.equals("2") && !newUse.equals("3")) {
                	System.out.println(
                    		"Enter new use:\n" +
                    		"1. Business\n" +
                    		"2. Personal\n" +
                    		"3. Both\n");
                	newUse = scan.nextLine();
                }
                
                if (newUse.equals("1")) {
                    update.updateUse("Business");
                }
                else if (newUse.equals("2")) {
                	update.updateUse("Personal");
                }
                else {
                	update.updateUse("Both");
                }
            }
            else if (choice.equals("4")) {
                System.out.println(
                		"Enter new status:\n" +
                		"1. Active\n" +
                		"2. Inactive\n");
                String newStatus = scan.nextLine();
                
                while (!newStatus.equals("1") && !newStatus.equals("2")) {
                	System.out.println(
                    		"Enter new status:\n" +
                    		"1. Active\n" +
                    		"2. Inactive\n");
                	newStatus = scan.nextLine();
                }
                
                if (newStatus.equals("1")) {
                    update.updateStatus("Active");
                }
                else if (newStatus.equals("2")) {
                	update.updateStatus("Inactive");
                }
            }
            else if (choice.equals("5")) {
                System.out.println("Enter new vehicle model:");
                update.getVehicleInfo().updateModel(scan.nextLine());
            }
            else if (choice.equals("6")) {
                System.out.println("Enter new vehicle year:");
                update.getVehicleInfo().updateYear(scan.nextLine());
            }
            else if (choice.equals("7")) {
                System.out.println("Enter new vehicle type:");
                update.getVehicleInfo().updateType(scan.nextLine());
            }
            else if (choice.equals("8")) {
                System.out.println("Enter new vehicle method:");
                update.getVehicleInfo().updateMethod(scan.nextLine());
	        }
        }
        
    }

    public void displayAllCustomerProf() {
        System.out.println("Please write the ID:");
        Scanner scan = new Scanner(System.in);
        String adminID = scan.nextLine();
        
        
        CustomerProf current = database.findNextProfile();
        while (current != null) {
            if (current.getAdminID().equals(adminID)) {
                displayCustomerProf(current);
            }
            current = database.findNextProfile();
        }
        
    }

    public void writeToDB() throws IOException {
        database.writeAllCustomerProf();
    }
}
