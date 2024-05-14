

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
//        Button button = new Button("Click me!");
//        System.out.println("Hello");
//        
//        button.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				System.out.println("Hello, JavaFX!");
//			}
//		});
//
//
        StackPane root = new StackPane();
      

        Scene scene = new Scene(root, 300, 250);
        
        // Đặt Scene vào Stage
        primaryStage.setScene(scene);
        
        // Đặt tiêu đề cho Stage
        primaryStage.setTitle("JavaFX Example");
        
        // Hiển thị Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
