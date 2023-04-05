package setting;

import java.util.ArrayList;
import java.util.List;

import employee.Employee;
import javafx.collections.ObservableList;
import position.Position;

public class PositionManagementSystem {
    private List<Position> positions;
    
    /**
     * Tạo danh sách chức vụ
     */
    public PositionManagementSystem() {
        this.positions = new ArrayList<>();
    }
	
    public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	/**
	 * 
	 * @param position
	 * Thêm chức vụ
	 */
	public void addPosition(Position position) {
        positions.add(position);
    }

	/**
	 * 
	 * @param position
	 * Xóa chức vụ
	 */
    public void removePosition(Position position) {
        positions.remove(position);
    }

    /**
     * 
     * @param index
     * @param ID
     * Chỉnh sửa ID của chức vụ dựa theo index
     */
    public void updatePositionID(int index, int ID) {
    	positions.get(index).setID(ID);
    }
    
    /**
     * 
     * @param index
     * @param name
     * Chỉnh sửa tên của chức vụ dựa theo index
     */
    public void updatePositionName(int index, String name) {
    	positions.get(index).setName(name);
    }
    
    /**
     * 
     * @param index
     * @param salaryCoefficient
     * Chỉnh sửa hệ số lương của chức vụ dựa theo index
     */
    public void updatePositionSalaryCoefficient(int index, double salaryCoefficient) {
    	positions.get(index).setSalaryCoefficient(salaryCoefficient);
    }
}
