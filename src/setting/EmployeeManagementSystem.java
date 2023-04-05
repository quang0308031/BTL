package setting;

import java.util.ArrayList;
import java.util.List;
import employee.Employee;
import setting.PositionManagementSystem;

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
    
    public void updateEmployeePosition(int index, String position, PositionManagementSystem positions) {
    	employeeList.get(index).setPositionName(position);
    	employeeList.get(index).setSalary(position, positions);
    }
    
    public void updateEmployeeSalary(String position, PositionManagementSystem positions) {
    	for (Employee temp: this.employeeList) {
    		temp.setSalary(position, positions);
    	}
    }
    
    public void updateEmployeeSalaryCoefficient(int index, double salaryCoefficient) {
    	employeeList.get(index).setSalary(salaryCoefficient);
    }
}
