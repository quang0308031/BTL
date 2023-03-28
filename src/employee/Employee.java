package employee;

public class Employee extends Person{
	private String ID = new String();
	private String position = new String();
	private String department = new String();
	private String job_type = new String();
	private String working_facilities = new String();
	private String entry_date = new String();
	private double salary = 0.0;
	
	public Employee(String name, String birth, int age, String gender, String address, String ethnic, String iD,
			String position, String department, String job_type, String working_facilities, String entry_date) {
		super(name, birth, age, gender, address, ethnic);
		ID = iD;
		this.position = position;
		this.department = department;
		this.job_type = job_type;
		this.working_facilities = working_facilities;
		this.entry_date = entry_date;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getWorking_facilities() {
		return working_facilities;
	}
	public void setWorking_facilities(String working_facilities) {
		this.working_facilities = working_facilities;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
}
