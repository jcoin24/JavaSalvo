package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import  javafx.scene.control.Button;
import org.w3c.dom.Text;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{

    public GridPane playerGrid;

    public Button checkHorizontal;
    boolean orientation = false;


    public void detectClick(MouseEvent mouseEvent){
        System.out.println("Row: " + GridPane.getRowIndex((Node)mouseEvent.getSource()) + " Column: " + GridPane.getColumnIndex((Node) mouseEvent.getSource()));
    }

    public void selectBattleship(ActionEvent actionEvent) {
        Main.setCurrentBoat(0);
        System.out.println("Current boat is a battleship");
        checkHorizontal.setText("Set Horizontal");
    }

    public void selectCruiser(ActionEvent actionEvent) {
        Main.setCurrentBoat(1);
        System.out.println("Current Boat is a cruiser");
        checkHorizontal.setText("Set Horizontal");
    }

    public void setHorizontal(ActionEvent actionEvent) {
        Main.SetCurrentBoatHorizontal();
        if(!orientation){
            checkHorizontal.setText("Set Vertical");
        }else {
            checkHorizontal.setText("Set Horizontal");
        }
        orientation = !orientation;

    }

    public void readyGame(ActionEvent actionEvent) {
        System.out.println("Game is ready");
    }

    public static void ChangeGui(int index){
        ((Button) Main.myList.get(index)).setText("Miss");
    }
}
