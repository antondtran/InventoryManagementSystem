package com.example.inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

// javadoc folder which is named 'javaDoc' is located above the src folder which contains the main java code.

public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainform.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();


        // exit button used to close window
        Button exitBtn = (Button) loader.getNamespace().get("exitBtn");
        exitBtn.setOnAction(event -> {
            stage.close();
        });
    }


    public static void main (String[]args){
        launch();
    }


}