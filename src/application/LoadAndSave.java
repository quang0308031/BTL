package application;

import java.io.File;
import java.util.List;


import file.FileDataAccessObject;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import position.Position;
import setting.EmployeeManagementSystem;
import setting.PositionManagementSystem;
import employee.Employee;
import application.GetPath;
import application.CustomFunction;

/**
 * 
 * @author Nguyễn Thanh Quang
 *
 * Lớp điều khiển lưu và tải dữ liệu.
 */
public class LoadAndSave {
	
	Stage primaryStage;
	/*
	 * Tạo list rỗng chứa thông tin danh sách nhân viên.
	 */
	private EmployeeManagementSystem dataEmp = new EmployeeManagementSystem();

	/*
	 * Tạo list rỗng chứa thông tin danh sách chức vụ.
	 */
	private PositionManagementSystem dataPos = new PositionManagementSystem();
	
	/*
	 * Tạo đối tượng phục vụ lưu và tải dữ liệu.
	 */
	private FileDataAccessObject<Employee> employeePath = new FileDataAccessObject<Employee>();
	private FileDataAccessObject<Position> positionPath = new FileDataAccessObject<Position>();

	public LoadAndSave() {}
	
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
	
	/**
	 * Tải dữ liệu nhân viên từ file chỉ định, báo lỗi nếu không mở được.
	 */
	public void loadDataEmp() {
		GetPath path = new GetPath();
		try {
			employeePath.setFileName(path.getPathToLoad());
			dataEmp.setEmployeeList(employeePath.load());
		} catch (Exception e) {
			if (path.getPath() != null) {
				CustomFunction.showErrorAlert("Không thể mở file từ đường dẫn này!");
			}
		}
	}
	
	/**
	 * Lưu dữ liệu nhân viên vào file chỉ định, báo lỗi nếu không lưu được.
	 */
	public void saveAsDataEmp() {
		GetPath path = new GetPath();
		try {
			FileDataAccessObject<Employee> temp = new FileDataAccessObject<Employee>(path.getPathToSave());
			temp.save(dataEmp.getEmployeeList());
		} catch (Exception e) {
			if (path.getPath() != null) {
				CustomFunction.showErrorAlert("Không thể lưu file vào đường dẫn này!");
			}
		}
	}
	
	/**
	 * Lưu dữ liệu nhân viên vào file tải lên trước đó.
	 */
	public void saveDataEmp() {
		employeePath.save(dataEmp.getEmployeeList());
	}
	
	/**
	 * Tải dữ liệu chức vụ từ file chỉ định, báo lỗi nếu không mở được.
	 */
	public void loadDataPos() {
		GetPath path = new GetPath();
		try {
			positionPath.setFileName(path.getPathToLoad());
			dataPos.setPositions(positionPath.load());
		} catch (Exception e) {
			if (path.getPath() != null) {
				CustomFunction.showErrorAlert("Không thể mở file từ đường dẫn này!");
			}
		}
	}
	
	/**
	 * Lưu dữ liệu chức vụ vào file chỉ định, báo lỗi nếu không lưu được.
	 */
	public void saveAsDataPos() {
		GetPath path = new GetPath();
		try {
			FileDataAccessObject<Position> temp = new FileDataAccessObject<Position>(path.getPathToSave());
			temp.save(dataPos.getPositions());
		} catch (Exception e) {
			if (path.getPath() != null) {
				CustomFunction.showErrorAlert("Không thể lưu file vào đường dẫn này!");
			}
		}
	}
	
	/**
	 * Lưu dữ liệu chức vụ vào file tải lên trước đó.
	 */
	public void saveDataPos() {
		positionPath.save(dataPos.getPositions());
	}
}
