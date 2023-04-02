<<<<<<< HEAD
package position;

import java.io.Serializable;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double salaryCoefficient;

    public Position(String name, double salaryCoefficient) {
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
    }

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
=======
package position;

import java.io.Serializable;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double salaryCoefficient;

    public Position(String name, double salaryCoefficient) {
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
    }

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
>>>>>>> 6df3917c2002ef1508a7f4621954eb2d1ab1d1a5
}