import java.util.Arrays;
import java.util.HashSet;

public class VehicleInfo {
	// fields
	private String model, year, type, method;
	// valid options for type and method
	private HashSet<String> validTypeTypes = new HashSet<>(Arrays.asList("Sedan", "Hatchback", "Luxury", "Sport", "Other"));
	private HashSet<String> validMethodTypes = new HashSet<>(Arrays.asList("New", "Certified Pre-Owned", "Used", "Other"));

	// constructor
	public VehicleInfo(String model, String year, String type, String method) {
		this.model = model;
		this.year = year;
		this.type = type;
		this.method = method;
	}
	
	// getter methods
	public String getModel() {
		return model;
	}
	public String getYear() {
		return year;
	}
	public String getType() {
		return type;
	}
	public String getMethod() {
		return method;
	}
	
	// setter methods
	public void updateModel(String newModel) {
		model = newModel;
	}
	public void updateYear(String newYear) {
		year = newYear;
	}
	public void updateType(String newType) {
		type = newType;
	}
	public void updateMethod(String newMethod) {
		method = newMethod;
	}
}
