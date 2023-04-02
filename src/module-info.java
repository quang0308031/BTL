module BTL {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens employee to javafx.base;
	opens position to javafx.base;
}
