package setting;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import position.Position;

public class PositionManagementSystem {
    private static ObservableList<Position> positions;
	
    public PositionManagementSystem(ObservableList<Position> positions) {
        PositionManagementSystem.positions = positions;
    }

    public static List<String> getPositionNames(){
    	List<String> names = new ArrayList<>();
    	for(Position i: positions) {
    		names.add(i.getPositionName());
    	}
    	return names;
    }
}
