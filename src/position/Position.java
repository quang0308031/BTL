package position;

public class Position {
	private String position_ID = new String();
	private String position_name = new String();
	private String type = new String();
	private double salary = 0.0;
	private String describe = new String();
	public Position(String position_ID, String position_name, String type, double salary, String describe) {
		super();
		this.position_ID = position_ID;
		this.position_name = position_name;
		this.type = type;
		this.salary = salary;
		this.describe = describe;
	}
	public String getPositionID() {
		return position_ID;
	}
	public void setPositionID(String position_ID) {
		this.position_ID = position_ID;
	}
	public String getPositionName() {
		return position_name;
	}
	public void setPositionName(String position_name) {
		this.position_name = position_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
