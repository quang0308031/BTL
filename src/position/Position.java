package position;

import java.io.Serializable;

/**
 * 
 * @author Nguyễn Thanh Quang
 *
 * Lớp chức vụ.
 */
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ID;
    private String name;
    private double salaryCoefficient;

    public Position(int ID, String name, double salaryCoefficient) {
    	this.ID = ID;
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
    }

    public Position() {};

	public String getName() {
        return name;
    }

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalaryCoefficient(double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}