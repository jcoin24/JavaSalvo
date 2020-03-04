package sample;

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

public class Controller {

    public GridPane playerGrid;


    public void detectClick(MouseEvent mouseEvent){
        System.out.println("Row: " + GridPane.getRowIndex((Node)mouseEvent.getSource()) + " Column: " + GridPane.getColumnIndex((Node) mouseEvent.getSource()));
    }
}
