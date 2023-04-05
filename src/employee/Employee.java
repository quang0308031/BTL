package employee;

import java.io.Serializable;

import position.Position;
import setting.PositionManagementSystem;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double salary;
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
        return this.salary - this.bonus;
    }

    public double getSalary() {
    	return this.salary;
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
    
    /**
     * 
     * @param position
     * @param positions
     * Tính lương dựa trên tiền thưởng và hệ số lương
     */
    public void setSalary(String position, PositionManagementSystem positions) {
    	double salary_Default = 0.0;
    	for(Position temp: positions.getPositions()) {
    		if(position.equals(temp.getName())) {
    			salary_Default = temp.getSalaryCoefficient();
    		}
    	}
    	this.salary = salary_Default + this.bonus;
    }
    
    public void setSalary(double salary) {
        this.salary = Math.round(salary);
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