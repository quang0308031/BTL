package setting;

import java.util.ArrayList;
import java.util.List;
import employee.Employee;
import setting.PositionManagementSystem;

/**
 * 
 *
 * Lớp quản lí danh sách nhân viên.
 */
public class EmployeeManagementSystem {
    private List<Employee> employeeList;

    /**
     * Tạo danh sách nhân viên rỗng.
     */
    public EmployeeManagementSystem() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) throws Exception {
		this.employeeList = employeeList;
	}
	
	/**
	 * 
	 * @param employee
	 * Thêm nhân viên.
	 */
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    /**
     * 
     * @param employee
     * Xóa nhân viên.
     */
    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    /**
     * 
     * @param index
     * @param ID
     * Chỉnh sửa ID của nhân viên dựa theo index.
     */
    public void updateEmployeeID(int index, int ID) {
    	employeeList.get(index).setId(ID);
    }
    
    /**
     * 
     * @param index
     * @param name
     * Chỉnh sửa tên của nhân viên dựa theo index.
     */
    public void updateEmployeeName(int index, String name) {
    	employeeList.get(index).setName(name);
    }
    
    /**
     * 
     * @param index
     * @param position
     * @param positions
     * Chỉnh sửa chức vụ đồng thời chỉnh sửa lương của nhân viên theo chức vụ dựa theo index.
     */
    public void updateEmployeePosition(int index, String position, PositionManagementSystem positions) {
    	employeeList.get(index).setPositionName(position);
    	employeeList.get(index).setSalary(position, positions);
    }
    
    /**
     * 
     * @param position
     * @param positions
     * Chỉnh sửa lương của nhân viên theo chức vụ dựa theo index.
     */
    public void updateEmployeeSalary(String position, PositionManagementSystem positions) {
    	for (Employee temp: this.employeeList) {
    		temp.setSalary(position, positions);
    	}
    }
    
    /**
     * 
     * @param index
     * @param salary
     * Chỉnh sửa lương của nhân viên dựa theo index.
     */
    public void updateEmployeeSalary(int index, double salary) {
    	employeeList.get(index).setSalary(salary);
    }
    
    /**
     * 
     * @param index
     * @param salary
     * Chỉnh sửa tiền thưởng của nhân viên dựa theo index.
     */
    public void updateEmployeeBonus(int index, double salary) {
    	employeeList.get(index).setBonus(salary);
    }
}
