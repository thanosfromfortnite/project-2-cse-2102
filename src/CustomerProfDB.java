import java.util.*;
import java.io.*;
public class CustomerProfDB {
    private List<CustomerProf> profiles;
    private int profileCounter;
    private int numCustomers;
    String fileName;
    private HashSet<String> validAdminIds = new HashSet<>(Arrays.asList("PA1", "PA2", "PA3"));

    public CustomerProfDB(String fileName) throws FileNotFoundException {
        profiles = new ArrayList<>();
        profileCounter = 0;
        this.fileName = fileName;
        initializeDB(fileName);
        numCustomers = profiles.size();
    }

    // Returns customer profiles list
    public List<CustomerProf> getCustomerList() {
        return profiles;
    }

    // Returns valid admin IDs
    public HashSet<String> getValidAdminIDs() {
        return validAdminIds;
    }

    // Checks if a customer profile exists for the admin ID
    public boolean existsMoreThanOne(String adminID) {
        boolean exists = false;
        int count = 0;
        // Loop through customer profiles
        for (CustomerProf customerProf : profiles) {
            // If profile matches admin ID, return true
            if (customerProf.getAdminID().equals(adminID)) {
                count++;
                if (count > 1) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    public void initializeDB(String filename) throws FileNotFoundException {
    	Scanner scan = new Scanner(new File(filename));
    	while (scan.hasNextLine()) { // while there is still data left to be read in the file


    		String[] vars = scan.nextLine().split(",");

    		// creates an array of all of the values separated by commas in the file.
    		// this assumes the file is formatted correctly
    		CustomerProf newCustomer = new CustomerProf(
    				vars[0], // adminID
    				vars[1], // first name
    				vars[2], // last name
    				vars[3], // address
    				vars[4], // phone number
    				vars[5], // income (float)
    				vars[6], // status
    				vars[7], // use
    				new VehicleInfo(
    						vars[8], // model
    						vars[9], // year
    						vars[10], // type
    						vars[11]) // method
    				);
            try {
                CustomerProf existingProf = findProfile(newCustomer.getAdminID(), newCustomer.getLastName());
            } catch (IllegalArgumentException e) {
                insertNewProfile(newCustomer);
            }
    	}
    }

    public void insertNewProfile(CustomerProf insert) {
        profiles.add(insert);
        numCustomers ++;
    }

    public CustomerProf findProfile(String adminID, String lastName) {
        for (CustomerProf i: profiles) { // iterate through whole list, return i if found, otherwise return null
            if (i.getAdminID().toLowerCase().equals(adminID.toLowerCase()) && i.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                return i;
            }
        }
        throw new IllegalArgumentException("Customer profile does not exist.");
    }

    public boolean deleteProfile(String adminID, String lastName) {
        CustomerProf x = findProfile(adminID, lastName);
        if (x == null) {
            return false;
        }
        else {
            profiles.remove(x);
            numCustomers --;
            return true;
        }
    }

    public CustomerProf findFirstProfile(String adminID) {
        // Iterate through list of customer profiles and return first profile that matches adminID input
        for (CustomerProf i : profiles) {
            if (i.getAdminID().equals(adminID)) {
                profileCounter = profiles.indexOf(i);
                return i;
            }
        }
        // Throw error if profile cannot be found
        throw new IllegalArgumentException("Customer profile not found.");
    }

    public CustomerProf findNextProfile(String adminID) {
        profileCounter++;
        for (int i = profileCounter; i < numCustomers; i++) {
            if (profiles.get(i).getAdminID().equals(adminID)) {
                profileCounter = i;
                return profiles.get(profileCounter);
            }
        }
        throw new IllegalArgumentException("Customer profile not found.");
    }

    public void writeAllCustomerProf() throws IOException {
    	File file = new File(fileName);

    	// THIS WILL CLEAR THE FILE before writing to it again!
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		file.createNewFile();

		PrintStream fileStream = new PrintStream(file);

    	for (CustomerProf i: profiles) {
    		String output = ""; // creates a string to compile all of the data into a CSV format
    		output += i.getAdminID() + ",";
    		output += i.getFirstName() + ",";
    		output += i.getLastName() + ",";
    		output += i.getAddress() + ",";
    		output += i.getPhone() + ",";
    		output += i.getIncome() + ",";
    		output += i.getStatus() + ",";
    		output += i.getUse() + ",";
    		output += i.getVehicleInfo().getModel() + ",";
    		output += i.getVehicleInfo().getYear() + ",";
    		output += i.getVehicleInfo().getType() + ",";
    		output += i.getVehicleInfo().getMethod();


    		fileStream.println(output); // writes the output to the file
    	}

    	fileStream.close();
    }



}
