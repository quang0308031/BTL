package application;

import java.util.List;

import employee.Employee;
import file.FileDataAccessObject;
import position.Position;
import setting.EmployeeManagementSystem;
import setting.PositionManagementSystem;

public class LoadAndSave {
	/*
	 * Tạo list rỗng chứa thông tin danh sách nhân viên
	 */
	private EmployeeManagementSystem dataEmp = new EmployeeManagementSystem();
	/*
	 * Tạo list rỗng chứa thông tin danh sách chức vụ
	 */
	private PositionManagementSystem dataPos = new PositionManagementSystem();
	
	private FileDataAccessObject<Employee> employeePath = new FileDataAccessObject<Employee>("C:\\Users\\quang\\Documents\\BTL\\data\\employee.bin");
	private FileDataAccessObject<Position> positionPath = new FileDataAccessObject<Position>("C:\\Users\\quang\\Documents\\BTL\\data\\position.bin");
	
	public EmployeeManagementSystem getDataEmp() {
		return dataEmp;
	}
	public void setDataEmp(EmployeeManagementSystem dataEmp) {
		this.dataEmp = dataEmp;
	}
	public PositionManagementSystem getDataPos() {
		return dataPos;
	}
	public void setDataPos(PositionManagementSystem dataPos) {
		this.dataPos = dataPos;
	}
	
	public List<Employee> loadDataEmp() {
		dataEmp.setEmployeeList(employeePath.load());
		return dataEmp.getEmployeeList();
		
	}
	
	public void saveDataEmp() {
		employeePath.save(dataEmp.getEmployeeList());
	}
	
	public List<Position> loadDataPos() {
		dataPos.setPositions(positionPath.load());
		return dataPos.getPositions();
	}
	
	public void saveDataPos() {
		positionPath.save(dataPos.getPositions());
	}

}
