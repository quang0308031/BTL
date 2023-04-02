package application;

import employee.Employee;
import position.Position;
import setting.EmployeeManagementSystem;
import setting.PositionManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Controller implements Initializable{
	
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
    private TextField employeeSearch;

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
    private TextField positionSearch;

    @FXML
    private TableView<Employee> tbEmployee;

    @FXML
    private TableView<Position> tbPosition;
    
    public void changeEmpID (CellEditEvent edditedcell) {
    	int index = tbEmployee.getSelectionModel().getSelectedIndex();
    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
    	for (Employee temp: employeeList) {
			if(temp.getId() == temp_ID && empIDcol.getCellData(index) != temp_ID) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("ID đã được sử dụng!");
				alert.show();
				updateTableEmployee();
				return;
			}
    	}
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setId(temp_ID);
    	dataEmp.updateEmployeeID(employeeList.indexOf(employeeSlected), temp_ID);
    }
    
    public void changeEmpName (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setName(edditedcell.getNewValue().toString().trim());
    	dataEmp.updateEmployeeName(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim());
    }
    
    public void changeEmpPosition (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setPositionName(edditedcell.getNewValue().toString().trim());
    	dataEmp.updateEmployeePosition(employeeList.indexOf(employeeSlected), edditedcell.getNewValue().toString().trim());
    }
    
    public void changeEmpSalary (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setSalaryCoefficient(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    	dataEmp.updateEmployeeSalaryCoefficient(employeeList.indexOf(employeeSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    }
    
    public void changePosID (CellEditEvent edditedcell) {
    	int index = tbPosition.getSelectionModel().getSelectedIndex();
    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString().trim());
    	for (Position temp: positionList) {
			if(temp.getID() == temp_ID && posIDcol.getCellData(index) != temp_ID) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("ID đã được sử dụng!");
				alert.show();
				updateTablePosition();
				return;
			}
    	}
    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
    	positionSlected.setID(temp_ID);
    	dataPos.updatePositionID(index, temp_ID);
    }
    
    public void changePosName (CellEditEvent edditedcell) {
    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
    	positionSlected.setName(edditedcell.getNewValue().toString().trim());
    	dataPos.updatePositionName(positionList.indexOf(positionSlected), edditedcell.getNewValue().toString().trim());
    }
    
    public void changePosSalary (CellEditEvent edditedcell) {
    	Position positionSlected = tbPosition.getSelectionModel().getSelectedItem();
    	positionSlected.setSalaryCoefficient(Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    	dataPos.updatePositionSalaryCoefficient(positionList.indexOf(positionSlected), Double.parseDouble(edditedcell.getNewValue().toString().trim()));
    }
    
    public void updateTableEmployee() {
    	empIDcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		empPositioncol.setCellValueFactory(new PropertyValueFactory<Employee, String>("positionName"));
		empSalarycol.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salaryCoefficient"));
		tbEmployee.setItems(employeeList);
		tbEmployee.setEditable(true);
		empIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		empNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		empPositioncol.setCellFactory(TextFieldTableCell.forTableColumn());
		empSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }
    
    public void updateTablePosition() {
    	posIDcol.setCellValueFactory(new PropertyValueFactory<Position, Integer>("ID"));
		posNamecol.setCellValueFactory(new PropertyValueFactory<Position, String>("name"));
		posSalarycol.setCellValueFactory(new PropertyValueFactory<Position, Double>("salaryCoefficient"));
		tbPosition.setItems(positionList);
		tbPosition.setEditable(true);
		posIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		posNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		posSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTableEmployee();
		updateTablePosition();
	}
	
	@FXML
	public void Add (ActionEvent event) {
		if(!employeeID.getText().trim().equals("") && !employeeName.getText().trim().equals("") && !employeePosition.getText().trim().equals("")) {
			for (Employee temp: employeeList) {
				if(temp.getId() == Integer.parseInt(employeeID.getText().trim())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("ID đã được sử dụng!");
					alert.show();
					return;
				}
			}
			Employee newEmployee = new Employee();
			newEmployee.setId(Integer.parseInt(employeeID.getText().trim()));
			newEmployee.setName(employeeName.getText().trim());
			newEmployee.setPositionName(employeePosition.getText().trim());
			if(!employeeSalaryBonus.getText().trim().equals("")) {
				newEmployee.setBonus(Integer.parseInt(employeeSalaryBonus.getText().trim()));
				newEmployee.setSalaryCoefficient();
			}
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
	}
	
	public void AddPos (ActionEvent event) {
		if(!positionID.getText().trim().equals("") && !positionName.getText().trim().equals("") && !positionSalary.getText().trim().equals("")) {
			for (Position temp: positionList) {
				if(temp.getID() == Integer.parseInt(positionID.getText().trim())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("ID đã được sử dụng!");
					alert.show();
					return;
				}
			}
			Position newPosition = new Position();
			newPosition.setID(Integer.parseInt(positionID.getText().trim()));
			newPosition.setName(positionName.getText().trim());
			newPosition.setSalaryCoefficient(Double.parseDouble(positionSalary.getText().trim()));
			
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
	}
}
