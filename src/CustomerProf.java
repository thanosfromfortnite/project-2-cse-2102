import java.util.Arrays;
import java.util.HashSet;

public class CustomerProf {
	// fields
	private String adminID, firstName, lastName, address, phone, status, use;
	private VehicleInfo vehicleInfo;
	private float income;
	// valid options for status and use
	private HashSet<String> validStatusTypes = new HashSet<>(Arrays.asList("Active", "Inactive"));
	private HashSet<String> validUseTypes = new HashSet<>(Arrays.asList("Business", "Personal", "Both"));

	// constructor
	public CustomerProf(String adminID, String firstName, String lastName, String address, String phone, String income, String status, String use, VehicleInfo vehicleInfo) {
		this.adminID = adminID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.use = use;
		this.income = Float.parseFloat(income);
		this.vehicleInfo = vehicleInfo;
	}
	// overloaded constructor, if an actual float is passed in
	public CustomerProf(String adminID, String firstName, String lastName, String address, String phone, float income, String status, String use, VehicleInfo vehicleInfo) {
		this.adminID = adminID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.use = use;
		this.income = income;
		this.vehicleInfo = vehicleInfo;
	}
	
	// methods
	// getter methods
	public String getAdminID() {
		return adminID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public float getIncome() {
		return income;
	}
	public String getStatus() {
		return status;
	}
	public String getUse() {
		return use;
	}
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}
	
	// setter methods
	public void updateFirstName(String str) {
		firstName = str;
	}
	public void updateLastName(String str) {
		lastName = str;
	}
	public void updateAddress(String str) {
		address = str;
	}
	public void updatePhone(String str) {
		phone = str;
	}
	public void updateUse(String str) {
		use = str;
	}
	public void updateIncome(float newIncome) {
		income = newIncome;
	}
	public void updateStatus(String str) {
		status = str;
	}
	public void updateVehicleInfo(VehicleInfo newVehicleInfo) {
		vehicleInfo = newVehicleInfo;
	}
}
