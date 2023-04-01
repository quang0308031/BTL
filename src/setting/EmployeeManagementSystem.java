package setting;

import java.util.ArrayList;
import java.util.List;
import employee.Employee;
import position.Position;

public class EmployeeManagementSystem {
    private List<Employee> employeeList;
    private List<Position> positionList;

    public EmployeeManagementSystem() {
        this.employeeList = new ArrayList<>();
        this.positionList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    public void updateEmployee(Employee employee) {
        // find employee by name and update information
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

    public void addPosition(Position position) {
        positionList.add(position);
    }

    public void removePosition(Position position) {
        positionList.remove(position);
    }

    public void updatePosition(Position position) {
        // find position by name and update information
    }
}

   
