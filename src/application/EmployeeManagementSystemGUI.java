package application;

import employee.Employee;
import position.Position;
import setting.PositionManagementSystem;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;


public class EmployeeManagementSystemGUI extends Application {
    private TableView<Employee> employeeTable;
    private TableView<Position> positionTable;

    private ObservableList<Employee> employeeList;
    private ObservableList<Position> positionList;

    @Override
    public void start(Stage primaryStage) {
        // create employee table
        employeeTable = new TableView<>();
        employeeTable.setEditable(true);

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Employee employee = event.getTableView().getItems().get(event.getTablePosition().getRow());
            employee.setName(event.getNewValue());
            // update employee in memory and file
        });

        TableColumn<Employee, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        /*positionColumn.setCellFactory(ComboBoxTableCell.forTableColumn(PositionManagementSystem.getPositionNames()));*/
        positionColumn.setOnEditCommit(event -> {
            Employee employee = event.getTableView().getItems().get(event.getTablePosition().getRow());
            employee.setPosition(event.getNewValue());
            // update employee in memory and file
        });

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary Coefficient");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salaryCoefficient"));
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        salaryColumn.setOnEditCommit(event -> {
            Employee employee = event.getTableView().getItems().get(event.getTablePosition().getRow());
            employee.setSalary(event.getNewValue());
            // update employee in memory and file
        });

        employeeTable.getColumns().addAll(nameColumn, positionColumn, salaryColumn);

        // create position table
        positionTable = new TableView<>();
        positionTable.setEditable(true);

        TableColumn<Position, String> positionNameColumn = new TableColumn<>("Position Name");
        positionNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionNameColumn.setOnEditCommit(event -> {
            Position position = event.getTableView().getItems().get(event.getTablePosition().getRow());
            position.setPositionName(event.getNewValue());
            // update position in memory and file
        });

        TableColumn<Position, Double> salaryCoefficientColumn = new TableColumn<>("Salary Coefficient");
        salaryCoefficientColumn.setCellValueFactory(new PropertyValueFactory<>("salaryCoefficient"));
        salaryCoefficientColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        salaryCoefficientColumn.setOnEditCommit(event -> {
            Position position = event.getTableView().getItems().get(event.getTablePosition().getRow());
            position.setSalary(event.getNewValue());
            // update position in memory and file
        });

        positionTable.getColumns().addAll(positionNameColumn, salaryCoefficientColumn);

        // create employee pane
        BorderPane employeePane = new BorderPane();
        employeePane.setTop(new Label("Employees"));
        employeePane.setCenter(employeeTable);

        Button addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(event -> {
            // show dialog to add employee
        });

        Button removeEmployeeButton = new Button("Remove Employee");
        removeEmployeeButton.setOnAction(event -> {
            // remove selected employee from table and memory
        });

        Button updateEmployeeButton = new Button("Update Employee");
        updateEmployeeButton.setOnAction(event -> {
            // update selected employee in table and memory
        });
    }
        public static void main(String[] args) {
    		launch(args);
    	}
}