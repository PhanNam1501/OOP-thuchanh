module JavaFx_Tutorials {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
}
