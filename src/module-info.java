<<<<<<< HEAD
module BTL {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens employee to javafx.base;
	opens position to javafx.base;
}
=======
module BTL {
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens employee to javafx.base;
	opens position to javafx.base;
}
>>>>>>> 6df3917c2002ef1508a7f4621954eb2d1ab1d1a5
