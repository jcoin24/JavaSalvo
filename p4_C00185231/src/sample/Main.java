package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Salvo");
        primaryStage.setScene(new Scene(root, 1000, 400));

        GridPane playerGrid = (GridPane) primaryStage.getScene().lookup("#playerGrid");

        int count = 1;

        for (int x = 0; x < playerGrid.getRowCount(); x++){
            for (int y = 0; y < playerGrid.getColumnCount(); y++){
                Button button = createButton(count);
                playerGrid.add(button, x, y);
                count++;
            }
        }

        ObservableList<Node> myList = playerGrid.getChildren();
        System.out.println(myList.get(5));

        primaryStage.show();
    }

    private Button createButton(int x) {
        Button button = new Button();
        button.setText("" + x);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> System.out.println(x));
        return button;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
