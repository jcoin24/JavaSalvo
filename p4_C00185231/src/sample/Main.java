package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    ObservableList<Node> myList;
    Boat battleship = new Boat(3,3,"Battleship", true);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Salvo");
        primaryStage.setScene(new Scene(root, 1000, 400));

        GridPane playerGrid = (GridPane) primaryStage.getScene().lookup("#playerGrid");
        HBox boatBox = (HBox) primaryStage.getScene().lookup("#boatColumn");

        int count = 1;

        for (int x = 0; x < playerGrid.getRowCount(); x++){
            for (int y = 0; y < playerGrid.getColumnCount(); y++){
                Button button = createButton(count);
                playerGrid.add(button, x, y);
                count++;
            }
        }

        myList = playerGrid.getChildren();
        primaryStage.show();
    }

    private Button createButton(int count) {
        Button button = new Button();
        button.setText("" + count);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> FillBoat(count));
        return button;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void FillBoat(int count){
        for (int x = 0; x < battleship.size; x++){
            Button temp = (Button) myList.get(count + x);
            temp.setText("X");
        }
    }
}
