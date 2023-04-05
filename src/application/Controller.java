package application;

import employee.Employee;
import position.Position;
import setting.EmployeeManagementSystem;
import setting.PositionManagementSystem;
import file.FileDataAccessObject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Nguyễn Thanh Quang
 *
 */
public class Controller implements Initializable{
	LoadAndSave data = new LoadAndSave();
	
	/*
	 * Tạo list rỗng chứa thông tin danh sách nhân viên
	 */
	private EmployeeManagementSystem dataEmp = data.getDataEmp();
	/*
	 * Tạo list rỗng chứa thông tin danh sách chức vụ
	 */
	private PositionManagementSystem dataPos = data.getDataPos();
	
    /*
     * Tạo dữ liệu cho tbEmployee
     */
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList(dataEmp.getEmployeeList());
    
    /*
     * Tạo dữ liệu cho tbPosition
     */
    private ObservableList<Position> positionList = FXCollections.observableArrayList(dataPos.getPositions());
	
	/**
	 * 
	 * @param contentText
	 * Hiển thị cửa sổ báo lỗi
	 */
	private void showErrorAlert(String contentText) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText("Error!");
	    alert.setContentText(contentText);
	    
	    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    stage.getIcons().add(new Image("file:C:\\Users\\quang\\Documents\\BTL\\icon\\error-icon-4.png"));
	    
	    alert.showAndWait();
	    
	}
	
	/**
	 * 
	 * @param contentText
	 * Hiển thị cửa sổ cảnh báo
	 */
	private void showWarningAlert(String contentText) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle("Waning");
	    alert.setHeaderText("Waning!");
	    alert.setContentText(contentText);
	    
	    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    stage.getIcons().add(new Image("file:C:\\Users\\quang\\Documents\\BTL\\icon\\6897039.png"));
	    
	    alert.showAndWait();
	}
	
	/**
	 * {@inheritDoc}
	 * Kiểm tra nếu String không phải dạng số thì trả về -1
	 */
	public static class CustomIntegerStringConverter extends IntegerStringConverter {
	    private final IntegerStringConverter converter = new IntegerStringConverter();

	    @Override
	    public String toString(Integer object) {
	        try {
	            return converter.toString(object);
	        } catch (NumberFormatException e) {
	        	System.out.println(e);
	        }
	        return null;
	    }

	    @Override
	    public Integer fromString(String string) {
	        try {
	            return converter.fromString(string);
	        } catch (NumberFormatException e) {
	        	System.out.println(e);
	        }
	        return -1;
	    }
	}
	
	/**
	 * {@inheritDoc}
	 * Kiểm tra nếu String không phải dạng số thì trả về -1
	 */
	public class CustomDoubleStringConverter extends StringConverter<Double> {
	    @Override
	    public String toString(Double value) {
	        if (value == null) {
	            return "";
	        }
	        return String.format("%.0f", value);
	    }

	    @Override
	    public Double fromString(String string) {
	        try {
	            return Double.parseDouble(string);
	        } catch (NumberFormatException e) {
	            return null;
	        }
	    }
	}
	
    @FXML
    private TableColumn<Employee, Integer> empIDcol;

    @FXML
    private TableColumn<Employee, String> empNamecol;

    @FXML
    private TableColumn<Employee, String> empPositioncol;

    @FXML
    private TableColumn<Employee, Double> empSalarycol;
    
    @FXML
    private TableColumn<Employee, Double> empBonuscol;
        
    @FXML
    private TextField employeeID;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField employeePosition;

    @FXML
    private TextField employeeSalary;

    @FXML
    private TextField employeeSalaryBonus;

    @FXML
    private TextField filterFieldEmp;

    @FXML
    private TableColumn<Position, Integer> posIDcol;

    @FXML
    private TableColumn<Position, String> posNamecol;

    @FXML
    private TableColumn<Position, Double> posSalarycol;

    @FXML
    private TextField positionID;

    @FXML
    private TextField positionName;

    @FXML
    private TextField positionSalary;

    @FXML
    private TextField positionSalaryS;

    @FXML
    private TextField filterFieldPos;

    @FXML
    private TableView<Employee> tbEmployee;

    @FXML
    private TableView<Position> tbPosition;
    
    /**
     * 
     * 
     * @param position
     * @return 0 nếu có chức vụ, 1 nếu không có chức vụ
     */
    public int checkPos(String position) {
    	int check = 0;
		for (Position checkPosition: positionList) {
			if(checkPosition.getName().equals(position)) {
				check = 1;
				break;
			}
		}
		return check;
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột empIDcol
     */
    public void changeEmpID (CellEditEvent edditedcell) {
    	int index = tbEmployee.getSelectionModel().getSelectedIndex();
    	if(edditedcell.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
	    	for (Employee temp: employeeList) {
				if(temp.getId() == temp_ID && empIDcol.getCellData(index) != temp_ID || temp_ID == -1) {
					if(temp_ID == -1) {
						showErrorAlert("ID phải là số nguyên!");
					}else {
						showErrorAlert("ID đã được sử dụng!");
					}
					updateTableEmployee();
					return;
				}
	    	}
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setId(temp_ID);
	    	dataEmp.updateEmployeeID(employeeList.indexOf(employeeSlected), temp_ID);
    	}else {
    		showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTableEmployee();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột empNamecol
     */
    public void changeEmpName (CellEditEvent edditedcell) {
    	if(!edditedcell.getNewValue().toString().trim().equals("")) {
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setName(edditedcell.getNewValue().toString().trim());
	    	dataEmp.updateEmployeeName(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim());
    	}else {
    		showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTableEmployee();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột empPositioncol
     */
    public void changeEmpPosition (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	if(checkPos(edditedcell.getNewValue().toString().trim()) == 0 && !edditedcell.getNewValue().toString().trim().equals("")) {
    		showWarningAlert("Cảnh báo hiện không có chức vụ này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo chức vụ này.\n* Xác nhận lại tại cột 'Chức vụ' để cập nhật lại lương thưởng cho nhân viên này.");
    	}
    	employeeSlected.setPositionName(edditedcell.getNewValue().toString().trim());
    	dataEmp.updateEmployeePosition(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim(), dataPos);
    	updateTableEmployee();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột empSalarycol
     */
    public void changeEmpBonus (CellEditEvent edditedcell) {
    	if(edditedcell.getNewValue() != null) {
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setBonus(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
	    	dataEmp.updateEmployeeBonus(employeeList.indexOf(employeeSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
	    	dataEmp.updateEmployeePosition(employeeList.indexOf(employeeSlected), employeeList.get(employeeList.indexOf(employeeSlected)).getPositionName(), dataPos);
    	}else {
    		Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setBonus(0.0);
	    	dataEmp.updateEmployeeBonus(employeeList.indexOf(employeeSlected), 0.0);
	    	dataEmp.updateEmployeePosition(employeeList.indexOf(employeeSlected), employeeList.get(employeeList.indexOf(employeeSlected)).getPositionName(), dataPos);
    	}
    	updateTableEmployee();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột empSalarycol
     */
    public void changeEmpSalary (CellEditEvent edditedcell) {
    	if(edditedcell.getNewValue() != null) {
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setSalary(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
	    	dataEmp.updateEmployeeSalary(employeeList.indexOf(employeeSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    	}else {
    		Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setSalary(0.0);
	    	dataEmp.updateEmployeeSalary(employeeList.indexOf(employeeSlected), 0.0);
    	}
    	updateTableEmployee();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột posIDcol
     */
    public void changePosID (CellEditEvent edditedcell) {
    	int index = tbPosition.getSelectionModel().getSelectedIndex();
    	if(edditedcell.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
	    	for (Position temp: positionList) {
				if(temp.getID() == temp_ID && posIDcol.getCellData(index) != temp_ID) {
					if(temp_ID == -1) {
						showErrorAlert("ID phải là số nguyên!");
					}else {
						showErrorAlert("ID đã được sử dụng!");
					}
					updateTablePosition();
					return;
				}
	    	}
	    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setID(temp_ID);
	    	dataPos.updatePositionID(index, temp_ID);
    	}else {
    		showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTablePosition();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột posNamecol
     */
    public void changePosName (CellEditEvent edditedcell) {
    	int index = tbPosition.getSelectionModel().getSelectedIndex();
    	if(!edditedcell.getNewValue().toString().trim().equals("")) {
    		for (Position temp: positionList) {
    			String temp_Name = edditedcell.getNewValue().toString().trim();
				if(temp.getName().equals(temp_Name)&& !posNamecol.getCellData(index).equals(temp_Name)) {
					showErrorAlert("Đã có chức vụ này!");
					updateTablePosition();
					return;
				}
	    	}
	    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setName(edditedcell.getNewValue().toString().trim());
	    	dataPos.updatePositionName(positionList.indexOf(positionSlected), edditedcell.getNewValue().toString().trim());
    	}else {
    		showErrorAlert("Không thể thay thế bằng chuỗi rỗng!");
    	}
    	updateTablePosition();
    }
    
    /**
     * 
     * @param edditedcell
     * Thay đổi một ô được chọn trong cột posSalarycol
     */
    public void changePosSalary (CellEditEvent edditedcell) {
    	if(edditedcell.getNewValue() != null) {
	    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setSalaryCoefficient(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
	    	dataPos.updatePositionSalaryCoefficient(positionList.indexOf(positionSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    	}else {
    		Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setSalaryCoefficient(0.0);
	    	dataPos.updatePositionSalaryCoefficient(positionList.indexOf(positionSlected), 0.0);
    	}
    	updateTablePosition();
    }
    
    /**
     * Update tbEmployee khi có thay đổi
     */
    public void updateTableEmployee() {
    	empIDcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		empPositioncol.setCellValueFactory(new PropertyValueFactory<Employee, String>("positionName"));
		empSalarycol.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
		empBonuscol.setCellValueFactory(new PropertyValueFactory<Employee, Double>("bonus"));
		tbEmployee.setItems(employeeList);
		tbEmployee.setEditable(true);
		empIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
		empNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		empPositioncol.setCellFactory(TextFieldTableCell.forTableColumn());
		try {
			empSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
		}catch(Exception e) {
			showErrorAlert("Tiền lương phải là số nguyên!");
			return;
		}
		try {
			empBonuscol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
		}catch(Exception e) {
			showErrorAlert("Tiền lương phải là số nguyên!");
			return;
		}
		search_employee_combined();
    }
    
    /**
     * Update tbPosition khi có thay đổi
     */
    public void updateTablePosition() {
    	posIDcol.setCellValueFactory(new PropertyValueFactory<Position, Integer>("ID"));
		posNamecol.setCellValueFactory(new PropertyValueFactory<Position, String>("name"));
		posSalarycol.setCellValueFactory(new PropertyValueFactory<Position, Double>("salaryCoefficient"));
		tbPosition.setItems(positionList);
		tbPosition.setEditable(true);
		posIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
		posNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		try {
			posSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
		}catch(Exception e) {
			showErrorAlert("Tiền lương phải là số nguyên!");
			return;
		}
		search_position_combined();
    }
	
	/**
	 * 
	 * @param event
	 * Thêm thông tin một nhân viên mới
	 */
	@FXML
	public void Add (ActionEvent event) {
		if(!employeeID.getText().trim().equals("") && !employeeName.getText().trim().equals("")) {
			for (Employee temp: employeeList) {
				if(temp.getId() == Integer.parseInt(employeeID.getText().trim())) {
					showErrorAlert("ID đã được sử dụng!");
					return;
				}
			}
			Employee newEmployee = new Employee();
			try {
				newEmployee.setId(Integer.parseInt(employeeID.getText().trim()));
			}catch(Exception e) {
				showErrorAlert("ID phải là số nguyên!");
				return;
			}
			newEmployee.setName(employeeName.getText().trim());
			if(checkPos(employeeName.getText().trim()) == 0 && !employeePosition.getText().trim().equals("")) {
				showWarningAlert("Cảnh báo hiện không có chức vụ này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo chức vụ này.\n* Xác nhận lại tại cột 'Chức vụ' để cập nhật lại lương thưởng cho nhân viên này.");
			}
			newEmployee.setPositionName(employeePosition.getText().trim());
			try {
				if(!employeeSalaryBonus.getText().trim().equals("")) {
					newEmployee.setBonus(Integer.parseInt(employeeSalaryBonus.getText().trim()));
				}
			}catch(Exception e) {
				showErrorAlert("Tiền thưởng phải là số nguyên!");
				return;
			}
			newEmployee.setSalary(employeePosition.getText().trim(), dataPos);
			employeeList.add(newEmployee);
			dataEmp.addEmployee(newEmployee);
			employeeName.setText("");
			employeeID.setText("");
			employeePosition.setText("");
			employeeSalaryBonus.setText("");
			updateTableEmployee();
		}else {
			showErrorAlert("Thiếu thông tin!");
		}
		search_employee_combined();
	}
	
	/**
	 * 
	 * @param event
	 * Thêm thông tin một chức vụ mới
	 */
	@FXML
	public void AddPos (ActionEvent event) {
		if(!positionID.getText().trim().equals("") && !positionName.getText().trim().equals("") && !positionSalary.getText().trim().equals("")) {
			for (Position temp: positionList) {
				if(temp.getID() == Integer.parseInt(positionID.getText().trim()) || temp.getName().equals(positionName.getText().trim())) {
					if(temp.getID() == Integer.parseInt(positionID.getText().trim())) {
						showErrorAlert("ID đã được sử dụng!");
					}else {
						showErrorAlert("Đã có chức vụ này!");
					} 
					
					return;
				}
			}
			Position newPosition = new Position();
			try {
				newPosition.setID(Integer.parseInt(positionID.getText().trim()));
			}catch(Exception e) {
				showErrorAlert("ID phải là số nguyên!");
				return;
			}
			newPosition.setName(positionName.getText().trim());
			try {
				newPosition.setSalaryCoefficient(Double.parseDouble(positionSalary.getText().trim()));
			}catch(Exception e) {
				showErrorAlert("Tiền lương phải là số nguyên!");
				return;
			}
			positionList.add(newPosition);
			dataPos.addPosition(newPosition);
			positionName.setText("");
			positionID.setText("");
			positionSalary.setText("");
			updateTablePosition();
		}else {
			showErrorAlert("Thiếu thông tin!");
		}
		search_position_combined();
	}
	
	/**
	 * 
	 * @param event
	 * Xóa một nhân viên được chỉ định
	 */
	@FXML
	public void Remove (ActionEvent event) {
		Employee selected = tbEmployee.getSelectionModel().getSelectedItem();
		if (selected == null) {
			showErrorAlert("Chọn nhân viên trước khi xóa!");
		}
		employeeList.remove(selected);
		dataEmp.removeEmployee(selected);
		search_employee_combined();
	}
	
	/**
	 * 
	 * @param event
	 * Xóa một chức vụ được chỉ định
	 */
	@FXML
	public void RemovePos (ActionEvent event) {
		Position selected = tbPosition.getSelectionModel().getSelectedItem();
		if (selected == null) {
			showErrorAlert("Chọn chức vụ trước khi xóa!");
		}
		positionList.remove(selected);
		dataPos.removePosition(selected);
		search_position_combined();
	}
	
	/**
	 * Lọc nhân viên theo từ khóa
	 */
	@Deprecated
	@FXML
	void search_employee() {
		FilteredList<Employee> filteredData = new FilteredList<>(employeeList, p -> true);

        filterFieldEmp.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(employee.getId()).contains(lowerCaseFilter)) {
                	return true;
                } else if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(employee.getSalary()).contains(lowerCaseFilter)) {
                	return true;
                } else if (employee.getPositionName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tbEmployee.comparatorProperty());
	
        tbEmployee.setItems(filteredData);
	}
	
	/**
	 * Lọc chức vụ theo từ khóa
	 */
	@Deprecated
	@FXML
	void search_position() {
		
		FilteredList<Position> filteredData = new FilteredList<>(positionList, p -> true);
        filterFieldPos.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(position -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(position.getID()).contains(lowerCaseFilter)) {
                	return true;
                } else if (position.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(position.getSalaryCoefficient()).contains(lowerCaseFilter)) {
                	return true;
                }
                
                return false;
            });
        });
        SortedList<Position> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tbPosition.comparatorProperty());
	
        tbPosition.setItems(filteredData);
	}
	
	/**
	 * Lọc chức vụ theo hệ số lương
	 */
	@Deprecated
	@FXML
	void search_position_salary() {
			FilteredList<Position> filteredData = new FilteredList<>(positionList, p -> true);	
			positionSalary.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(position -> {
	                if (newValue == null || newValue.trim().isEmpty()) {
	                    return true;
	                }
	                try {
	                	Double comparisionValue = Double.parseDouble(newValue);

	                	if (position.getSalaryCoefficient() >= comparisionValue) {
		                	return true;
		                } 

		                return false;
	                }catch(Exception e) {
	                	return true;
	                }
	            });
			});
	        SortedList<Position> sortedData = new SortedList<>(filteredData);

			sortedData.comparatorProperty().bind(tbPosition.comparatorProperty());

	        tbPosition.setItems(filteredData);

	}
	
	/**
	 * Lọc nhân viên theo hệ số lương
	 */
	@Deprecated
	@FXML
	void search_employee_salary() {
			FilteredList<Employee> filteredData = new FilteredList<>(employeeList, p -> true);	
			employeeSalary.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(employee -> {
	                if (newValue == null || newValue.trim().isEmpty()) {
	                    return true;
	                }
	                try {
		                Double comparisionValue = Double.parseDouble(newValue);
		                	
		                if (employee.getSalaryCoefficient() >= comparisionValue) {
			                return true;
			            } 
			                
			               return false;
	                }catch(Exception e) {
	                	return true;
	                }
	            });
			});
	        SortedList<Employee> sortedData = new SortedList<>(filteredData);
	
			sortedData.comparatorProperty().bind(tbEmployee.comparatorProperty());
		
	        tbEmployee.setItems(filteredData);
		
	}
	
	/*
	 * Tạo bảng đã được lọc tìm kiếm của tbEmployee
	 */
	private FilteredList<Employee> filteredDataEmp = new FilteredList<>(employeeList, p -> true);
	/**
	 * Lọc nhân viên theo từ khóa và hệ số lương
	 */
	@FXML
	void search_employee_combined() {

	    // Combine the textProperty listeners from the two previous methods
	    ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
	        filteredDataEmp.setPredicate(employee -> {
	            // Get the value from both fields
	            String filterValue = filterFieldEmp.getText().trim();
	            String salaryValue = employeeSalary.getText().trim();
	            Double comparisionValue;
	            try {
	                comparisionValue = Double.parseDouble(salaryValue);
	            } catch (Exception e) {
	                comparisionValue = null;
	            }

	            if (filterValue == null || filterValue.trim().isEmpty()) {
	                if (comparisionValue == null) {
	                    return true;
	                } else {
	                    return employee.getSalaryCoefficient() >= comparisionValue;
	                }
	            }

	            String lowerCaseFilter = filterValue.toLowerCase();
	            if (String.valueOf(employee.getId()).contains(lowerCaseFilter)) {
	                return comparisionValue == null || employee.getSalaryCoefficient() >= comparisionValue;
	            } else if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
	                return comparisionValue == null || employee.getSalaryCoefficient() >= comparisionValue;
	            } else if (employee.getPositionName().toLowerCase().contains(lowerCaseFilter)) {
	                return comparisionValue == null || employee.getSalaryCoefficient() >= comparisionValue;
	            } else if (String.valueOf(employee.getBonus()).contains(lowerCaseFilter)) {
	                return comparisionValue == null || employee.getSalaryCoefficient() >= comparisionValue;
	            } else if (String.valueOf(employee.getSalary()).contains(lowerCaseFilter)) {
	                return comparisionValue == null || employee.getSalaryCoefficient() >= comparisionValue;
	            }
	            return false;
	        });
	    };

	    filterFieldEmp.textProperty().addListener(changeListener);
	    employeeSalary.textProperty().addListener(changeListener);

	    SortedList<Employee> sortedData = new SortedList<>(filteredDataEmp);
	    sortedData.comparatorProperty().bind(tbEmployee.comparatorProperty());
	    tbEmployee.setItems(filteredDataEmp);
	}
	
	/*
	 * Tạo bảng đã được lọc tìm kiếm của tbPosition
	 */
	private FilteredList<Position> filteredDataPos = new FilteredList<>(positionList, p -> true);
	/**
	 * Lọc chức vụ theo từ khóa và hệ số lương
	 */
	@FXML
	void search_position_combined() {
	  
	    // Combine the textProperty listeners from the two previous methods
	    ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
	        filteredDataPos.setPredicate(position -> {
	            // Get the value from both fields
	            String filterValue = filterFieldPos.getText().trim();
	            String salaryValue = positionSalaryS.getText().trim();
	            Double comparisionValue;
	            try {
	                comparisionValue = Double.parseDouble(salaryValue);
	            } catch (Exception e) {
	                comparisionValue = null;
	            }

	            if (filterValue == null || filterValue.trim().isEmpty()) {
	                if (comparisionValue == null) {
	                    return true;
	                } else {
	                    return position.getSalaryCoefficient() >= comparisionValue;
	                }
	            }

	            String lowerCaseFilter = filterValue.toLowerCase();
	            if (String.valueOf(position.getID()).contains(lowerCaseFilter)) {
	                return comparisionValue == null || position.getSalaryCoefficient() >= comparisionValue;
	            } else if (position.getName().toLowerCase().contains(lowerCaseFilter)) {
	                return comparisionValue == null || position.getSalaryCoefficient() >= comparisionValue;
	            } else if (String.valueOf(position.getSalaryCoefficient()).contains(lowerCaseFilter)) {
	                return comparisionValue == null || position.getSalaryCoefficient() >= comparisionValue;
	            } 
	            return false;
	        });
	    };

	    filterFieldPos.textProperty().addListener(changeListener);
	    positionSalaryS.textProperty().addListener(changeListener);

	    SortedList<Position> sortedData = new SortedList<>(filteredDataPos);
	    sortedData.comparatorProperty().bind(tbPosition.comparatorProperty());
	    tbPosition.setItems(filteredDataPos);
	}

	/**
	 * Override hàm initialize của giao diện Initialize thuộc fxml
	 * + Hiển thị tbEmployee
	 * + Hiển thị tbPosition
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTableEmployee();
		updateTablePosition();
	}
	
	@FXML
	public void loadDataEmp() {
		dataEmp.setEmployeeList(data.loadDataEmp());
		employeeList = FXCollections.observableArrayList(dataEmp.getEmployeeList());
	
	}
	
	@FXML
	public void saveDataEmp() {
		data.saveDataEmp();
	}
	
	@FXML
	public void loadDataPos() {
		data.loadDataPos();
	}
	
	@FXML
	public void saveDataPos() {
		data.saveDataPos();
	}
}
