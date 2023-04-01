package application;

import employee.Employee;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Controller implements Initializable{
	
    @FXML
    private TableColumn<Employee, Integer> empIDcol;

    @FXML
    private TableColumn<Employee, String> empNamecol;

    @FXML
    private TableColumn<Employee, String> empPositioncol;

    @FXML
    private TableColumn<Employee, Double> empSalarycol;
    
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    
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
    private TableColumn<?, ?> posIDcol;

    @FXML
    private TableColumn<?, ?> posNamecol;

    @FXML
    private TableColumn<?, ?> posSalarycol;

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
    private TableView<?> tbPosition;
    
    public void changeEmpID (CellEditEvent edditedcell) {
    	int index = tbEmployee.getSelectionModel().getSelectedIndex();
    	int temp_ID = Integer.parseInt(edditedcell.getNewValue().toString());
    	for (Employee temp: employeeList) {
			if(temp.getId() == temp_ID && empIDcol.getCellData(index) != temp_ID) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("ID đã được sử dụng!");
				alert.show();
				return;
			}
    	}
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setId(temp_ID);
    }
    
    public void changeEmpName (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setName(edditedcell.getNewValue().toString());
    }
    
    public void changeEmpPosition (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setPositionName(edditedcell.getNewValue().toString());
    }
    
    public void changeEmpSalary (CellEditEvent edditedcell) {
    	Employee employeeSlected = tbEmployee.getSelectionModel().getSelectedItem();
    	employeeSlected.setSalaryCoefficient(Double.parseDouble(edditedcell.getNewValue().toString()));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeList = FXCollections.observableArrayList(
				new Employee(1, "Quang", 100, "tt"),
				new Employee(2, "Quang", 100, "tt")
				);
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
	
	@FXML
	public void Add (ActionEvent event) {
		if(!employeeID.getText().equals("") && !employeeName.getText().equals("") && !employeePosition.getText().equals("")) {
			for (Employee temp: employeeList) {
				if(temp.getId() == Integer.parseInt(employeeID.getText())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("ID đã được sử dụng!");
					alert.show();
					return;
				}
			}
			Employee newEmployee = new Employee();
			newEmployee.setId(Integer.parseInt(employeeID.getText()));
			newEmployee.setName(employeeName.getText());
			newEmployee.setPositionName(employeePosition.getText());
			if(!employeeSalaryBonus.getText().equals("")) {
				newEmployee.setBonus(Integer.parseInt(employeeSalaryBonus.getText()));
				newEmployee.setSalaryCoefficient();
			}
			employeeList.add(newEmployee);
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
	}
	
	Integer index;
	
	@FXML
    void getItem(MouseEvent event) {
		index = tbEmployee.getSelectionModel().getSelectedIndex();
		if(index < -1) {
			return;
		}
		employeeID.setText(empIDcol.getCellData(index).toString());
		employeeName.setText(empNamecol.getCellData(index).toString());
		employeePosition.setText(empPositioncol.getCellData(index).toString());
		employeeSalaryBonus.setText(String.valueOf(employeeList.get(index).getBonus()));
	}
}
