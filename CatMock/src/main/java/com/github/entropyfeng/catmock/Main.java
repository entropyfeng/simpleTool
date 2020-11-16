package com.github.entropyfeng.catmock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author entropyfeng
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");

        TableView<String> tableView=new TableView<>();
        root.setCenter(tableView);
        Scene scene= new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
