package application;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * 
 * @author Nguyễn Thanh Quang
 *
 * Lớp chứa hàm tùy chỉnh.
 */
public class CustomFunction {
	/**
	 * 
	 * @param contentText
	 * Hiển thị cửa sổ báo lỗi.
	 */
	public static void showErrorAlert(String contentText) {
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
	 * Hiển thị cửa sổ cảnh báo.
	 */
	public static void showWarningAlert(String contentText) {
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
	 * Kiểm tra nếu String không phải dạng số thì bắt lỗi.
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
	 * Kiểm tra nếu String không phải dạng số thì thì bắt lỗi.
	 */
	public static class CustomDoubleStringConverter extends StringConverter<Double> {
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
}
