package employee;


public class Employee {
    private int id;
    private String name;
    private double salaryCoefficient;
    private String positionName;
    private double bonus;

    public Employee(int id, String name, double bonus, String positionName) {
        this.id = id;
        this.name = name;
        this.bonus = Math.round(bonus);
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
        this.salaryCoefficient =  Math.round(this.salaryCoefficient + this.bonus);
    }
    
    public void setSalaryCoefficient(double salaryCoefficient) {
        this.salaryCoefficient = Math.round(salaryCoefficient);
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = Math.round(bonus);
	}
}
