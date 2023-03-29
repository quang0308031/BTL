package application;

import employee.Employee;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable{
	
    @FXML
    private TableColumn<Employee, Integer> empIDcol;

    @FXML
    private TableColumn<Employee, String> empNamecol;

    @FXML
    private TableColumn<Employee, String> empPositioncol;

    @FXML
    private TableColumn<Employee, Integer> empSalarycol;
    
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeList = FXCollections.observableArrayList(
				new Employee(1, "Quang", 100, "tt")
				);
		empIDcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		empPositioncol.setCellValueFactory(new PropertyValueFactory<Employee, String>("positionName"));
		empSalarycol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salaryCoefficient"));
		tbEmployee.setItems(employeeList);
		empIDcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		empIDcol.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setId(event.getNewValue());
        });
		empNamecol.setCellFactory(TextFieldTableCell.forTableColumn());
		empNamecol.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
        });
		empPositioncol.setCellFactory(TextFieldTableCell.forTableColumn());
		empPositioncol.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setPositionName(event.getNewValue());
        });
		empSalarycol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		empSalarycol.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setBonus(event.getNewValue());
            employee.setSalaryCoefficient();
        });
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
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Thêm thành công.");
			alert.show();
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
	
	Integer index = -2;
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
	
	public void Modify (ActionEvent event) {
		System.out.println(this.index);
		if(this.index < -1) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Chọn nhân viên trước khi sửa!");
			alert.show();
			return;
		}
		if(!employeeID.getText().equals("") && !employeeName.getText().equals("") && !employeePosition.getText().equals("")) {
			for (Employee temp: employeeList) {
				if(temp.getId() == Integer.parseInt(employeeID.getText())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("ID đã được sử dụng!");
					alert.show();
					return;
				}
			}
			employeeList.get(index).setId(Integer.parseInt(employeeID.getText()));
			employeeList.get(index).setName(employeeName.getText());
			employeeList.get(index).setPositionName(employeePosition.getText());
			if(!employeeSalaryBonus.getText().equals("")) {
				employeeList.get(index).setBonus(Integer.parseInt(employeeSalaryBonus.getText()));
				employeeList.get(index).setSalaryCoefficient();
			}else {
				employeeList.get(index).setBonus(0);
				employeeList.get(index).setSalaryCoefficient();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Sửa thành công.");
			alert.show();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Thiếu thông tin!");
			alert.show();
		}
	}
}
