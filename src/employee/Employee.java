package employee;


public class Employee {
    private int id;
    private String name;
    private int salaryCoefficient;
    private String positionName;
    private int bonus;

    public Employee(int id, String name, int bonus, String positionName) {
        this.id = id;
        this.name = name;
        this.bonus = bonus;
        this.positionName = positionName;
    }
    
    public Employee() {};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalaryCoefficient() {
        this.salaryCoefficient =  this.salaryCoefficient + this.bonus;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
