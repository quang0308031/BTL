package application;

import employee.Employee;
import position.Position;
import setting.EmployeeManagementSystem;
import setting.PositionManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Controller implements Initializable{
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
			
	EmployeeManagementSystem dataEmp = new EmployeeManagementSystem();
	PositionManagementSystem dataPos = new PositionManagementSystem();
	
    @FXML
    private TableColumn<Employee, Integer> empIDcol;

    @FXML
    private TableColumn<Employee, String> empNamecol;

    @FXML
    private TableColumn<Employee, String> empPositioncol;

    @FXML
    private TableColumn<Employee, Double> empSalarycol;
    
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList(dataEmp.getEmployeeList());
        
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
    
    private ObservableList<Position> positionList = FXCollections.observableArrayList(dataPos.getPositions());

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
    
    public void changeEmpID (CellEditEvent edditedcell) {
    	int index = tbEmployee.getSelectionModel().getSelectedIndex();
    	if(edditedcell.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
	    	for (Employee temp: employeeList) {
				if(temp.getId() == temp_ID && empIDcol.getCellData(index) != temp_ID || temp_ID == -1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					if(temp_ID == -1) {
						alert.setContentText("ID phải là số nguyên!");
					}else {
						alert.setContentText("ID đã được sử dụng!");
					}
					alert.show();
					updateTableEmployee();
					return;
				}
	    	}
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setId(temp_ID);
	    	dataEmp.updateEmployeeID(employeeList.indexOf(employeeSlected), temp_ID);
    	}else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Không thể thay thế bằng chuỗi rỗng!");
			alert.show();
    	}
    	updateTableEmployee();
    }
    
    public void changeEmpName (CellEditEvent edditedcell) {
    	if(!edditedcell.getNewValue().toString().trim().equals("")) {
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setName(edditedcell.getNewValue().toString().trim());
	    	dataEmp.updateEmployeeName(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim());
    	}else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Không thể thay thế bằng chuỗi rỗng!");
			alert.show();
    	}
    	updateTableEmployee();
    }
    
    public void changeEmpPosition (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	if(checkPos(edditedcell.getNewValue().toString().trim()) == 0 && !edditedcell.getNewValue().toString().trim().equals("")) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Cảnh báo hiện không có chức vụ này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo chức vụ này.\n* Xác nhận lại tại cột 'Chức vụ' để cập nhật lại lương thưởng cho nhân viên này.");
			alert.show();
    	}
    	employeeSlected.setPositionName(edditedcell.getNewValue().toString().trim());
    	dataEmp.updateEmployeePosition(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim(), dataPos);
    	updateTableEmployee();
    }
    
    public void changeEmpSalary (CellEditEvent edditedcell) {
    	if(edditedcell.getNewValue() != null) {
	    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setSalaryCoefficient(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
	    	dataEmp.updateEmployeeSalaryCoefficient(employeeList.indexOf(employeeSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    	}else {
    		Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
	    	employeeSlected.setSalaryCoefficient(0.0);
	    	dataEmp.updateEmployeeSalaryCoefficient(employeeList.indexOf(employeeSlected), 0.0);
    	}
    	updateTableEmployee();
    }
    
    public void changePosID (CellEditEvent edditedcell) {
    	int index = tbPosition.getSelectionModel().getSelectedIndex();
    	if(edditedcell.getNewValue() != null) {
	    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
	    	for (Position temp: positionList) {
				if(temp.getID() == temp_ID && posIDcol.getCellData(index) != temp_ID) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					if(temp_ID == -1) {
						alert.setContentText("ID phải là số nguyên!");
					}else {
						alert.setContentText("ID đã được sử dụng!");
					}
					alert.show();
					updateTablePosition();
					return;
				}
	    	}
	    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setID(temp_ID);
	    	dataPos.updatePositionID(index, temp_ID);
    	}else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Không thể thay thế bằng chuỗi rỗng!");
			alert.show();
    	}
    	updateTablePosition();
    }
    
    public void changePosName (CellEditEvent edditedcell) {
    	int index = tbPosition.getSelectionModel().getSelectedIndex();
    	if(!edditedcell.getNewValue().toString().trim().equals("")) {
    		for (Position temp: positionList) {
    			String temp_Name = edditedcell.getNewValue().toString().trim();
				if(temp.getName().equals(temp_Name)&& !posNamecol.getCellData(index).equals(temp_Name)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("Đã được có chức vụ này!");
					alert.show();
					updateTablePosition();
					return;
				}
	    	}
	    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
	    	positionSlected.setName(edditedcell.getNewValue().toString().trim());
	    	dataPos.updatePositionName(positionList.indexOf(positionSlected), edditedcell.getNewValue().toString().trim());
    	}else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Không thể thay thế bằng chuỗi rỗng!");
			alert.show();
    	}
    	updateTablePosition();
    }
    
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
    
    public void updateTableEmployee() {
    	empIDcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		empPositioncol.setCellValueFactory(new PropertyValueFactory<Employee, String>("positionName"));
		empSalarycol.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salaryCoefficient"));
		tbEmployee.setItems(employeeList);
		tbEmployee.setEditable(true);
		empIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
		empNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		empPositioncol.setCellFactory(TextFieldTableCell.forTableColumn());
		try {
			empSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
		}catch(Exception e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Tiền lương phải là số nguyên!");
			alert.show();
			return;
		}
		search_employee();
    }
    
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
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Tiền lương phải là số nguyên!");
			alert.show();
			return;
		}
		search_position();
    }
	
	/**
	 * 
	 * @param event: 
	 * 
	 * 
	 */
	@FXML
	public void Add (ActionEvent event) {
		if(!employeeID.getText().trim().equals("") && !employeeName.getText().trim().equals("")) {
			for (Employee temp: employeeList) {
				if(temp.getId() == Integer.parseInt(employeeID.getText().trim())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("ID đã được sử dụng!");
					alert.show();
					return;
				}
			}
			Employee newEmployee = new Employee();
			try {
				newEmployee.setId(Integer.parseInt(employeeID.getText().trim()));
			}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("ID phải là số nguyên!");
				alert.show();
				return;
			}
			newEmployee.setName(employeeName.getText().trim());
			if(checkPos(employeeName.getText().trim()) == 0 && !employeePosition.getText().trim().equals("")) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Cảnh báo hiện không có chức vụ này!\n* Nếu bạn muốn sử dụng tên này vui lòng tạo chức vụ này.\n* Xác nhận lại tại cột 'Chức vụ' để cập nhật lại lương thưởng cho nhân viên này.");
				alert.show();
			}
			newEmployee.setPositionName(employeePosition.getText().trim());
			try {
				if(!employeeSalaryBonus.getText().trim().equals("")) {
					newEmployee.setBonus(Integer.parseInt(employeeSalaryBonus.getText().trim()));
				}
			}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Tiền thưởng phải là số nguyên!");
				alert.show();
				return;
			}
			newEmployee.setSalaryCoefficient(employeePosition.getText().trim(), dataPos);
			employeeList.add(newEmployee);
			dataEmp.addEmployee(newEmployee);
			employeeName.setText("");
			employeeID.setText("");
			employeePosition.setText("");
			employeeSalaryBonus.setText("");
			updateTableEmployee();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Thiếu thông tin!");
			alert.show();
		}
		search_employee();
	}
	
	public void AddPos (ActionEvent event) {
		if(!positionID.getText().trim().equals("") && !positionName.getText().trim().equals("") && !positionSalary.getText().trim().equals("")) {
			for (Position temp: positionList) {
				if(temp.getID() == Integer.parseInt(positionID.getText().trim()) || temp.getName().equals(positionName.getText().trim())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					if(temp.getID() == Integer.parseInt(positionID.getText().trim())) {
						alert.setContentText("ID đã được sử dụng!");
					}else {
						alert.setContentText("Đã được có chức vụ này!");
					} 
					
					alert.show();
					return;
				}
			}
			Position newPosition = new Position();
			try {
				newPosition.setID(Integer.parseInt(positionID.getText().trim()));
			}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("ID phải là số nguyên!");
				alert.show();
				return;
			}
			newPosition.setName(positionName.getText().trim());
			try {
				newPosition.setSalaryCoefficient(Double.parseDouble(positionSalary.getText().trim()));
			}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Tiền lương phải là số nguyên!");
				alert.show();
				return;
			}
			positionList.add(newPosition);
			dataPos.addPosition(newPosition);
			positionName.setText("");
			positionID.setText("");
			positionSalary.setText("");
			updateTablePosition();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Thiếu thông tin!");
			alert.show();
			search_position();
		}
	}
	
	public void Remove (ActionEvent event) {
		Employee selected = tbEmployee.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Chọn nhân viên trước khi xóa!");
			alert.show();
		}
		employeeList.remove(selected);
		dataEmp.removeEmployee(selected);
		search_employee();
	}
	
	public void RemovePos (ActionEvent event) {
		Position selected = tbPosition.getSelectionModel().getSelectedItem();
		if (selected == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Chọn nhân viên trước khi xóa!");
			alert.show();
		}
		positionList.remove(selected);
		dataPos.removePosition(selected);
		search_position();
	}
	
	@FXML
	void search_employee() {
		FilteredList<Employee> filteredData = new FilteredList<>(employeeList, p -> true);

        filterFieldEmp.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(employee.getId()).contains(lowerCaseFilter)) {
                	return true;
                } else if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(employee.getSalaryCoefficient()).contains(lowerCaseFilter)) {
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
	
	@FXML
	void search_position() {
		FilteredList<Position> filteredData = new FilteredList<>(positionList, p -> true);

        filterFieldPos.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(position -> {
                if (newValue == null || newValue.isEmpty()) {
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
	
	@FXML
	void search_employee_salary() {
		FilteredList<Employee> filteredData = new FilteredList<>(employeeList, p -> true);

		employeeSalary.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
			    System.out.println("Salary Coefficient: " + employee.getSalaryCoefficient());
			    System.out.println("Bonus: " + employee.getBonus());
			    return (employee.getSalaryCoefficient() - employee.getBonus()) >= Double.parseDouble(newValue);
			});
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tbEmployee.comparatorProperty());
	
        tbEmployee.setItems(filteredData);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTableEmployee();
		updateTablePosition();
	}
}
