package setting;

import java.util.ArrayList;
import java.util.List;
import employee.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import position.Position;

public class EmployeeManagementSystem {
    private List<Employee> employeeList;

    public EmployeeManagementSystem() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    public void updateEmployeeID(int index, int ID) {
    	employeeList.get(index).setId(ID);
    }
    
    public void updateEmployeeName(int index, String name) {
    	employeeList.get(index).setName(name);
    }
    
    public void updateEmployeePosition(int index, String Position) {
    	employeeList.get(index).setPositionName(Position);
    }
    
    public void updateEmployeeSalaryCoefficient(int index, double salaryCoefficient) {
    	employeeList.get(index).setSalaryCoefficient(salaryCoefficient);
    }

	public List<Employee> getEmployeesBySalaryCoefficient(double salary) {
        List<Employee> filteredEmployees = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (employee.getSalaryCoefficient() > salary) {
                filteredEmployees.add(employee);
            }
        }

        return filteredEmployees;
    }
}
