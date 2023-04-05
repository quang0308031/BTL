package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("BTL.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("C:\\Users\\quang\\Documents\\BTL\\icon\\download.png"));
            primaryStage.setTitle("Quản lí nhân sự.");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}