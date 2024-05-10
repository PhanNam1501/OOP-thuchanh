package javafx.tutorials;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click me!");
        System.out.println("Hello");
        
        button.setOnAction(e -> System.out.println("Hello, JavaFX!"));


        StackPane root = new StackPane();
        root.getChildren().add(button);
        

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
