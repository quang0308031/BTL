package setting;

import java.util.ArrayList;
import java.util.List;

import employee.Employee;
import javafx.collections.ObservableList;
import position.Position;

public class PositionManagementSystem {
    private ArrayList<Position> positions;
    
    public PositionManagementSystem() {
        this.positions = new ArrayList<>();
    }
	
    public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

	public PositionManagementSystem(ObservableList<Position> positions) {
        this.positions = new ArrayList<Position>();
    }
	
	public void addPosition(Position position) {
        positions.add(position);
    }

    public void removePosition(Position position) {
        positions.remove(position);
    }

    public void updatePositionID(int index, int ID) {
    	positions.get(index).setID(ID);
    }
    
    public void updatePositionName(int index, String name) {
    	positions.get(index).setName(name);
    }
    
    public void updatePositionSalaryCoefficient(int index, double salaryCoefficient) {
    	positions.get(index).setSalaryCoefficient(salaryCoefficient);
    }
}
