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
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    // Declared list to have all the Nodes of the gridpane
    ObservableList<Node> myList;

    // Values for defining the ships and list to store them
    Boat battleship = new Boat(3,3,"Battleship", true);
    Boat cruiser = new Boat(2,2,"Cruiser", true);
    List<Boat> shipList = new ArrayList<Boat>();
    int currentBoat = 0;

    // Declare playerGrid variable
    GridPane playerGrid;

    @Override
    public void start(Stage primaryStage) throws Exception{

        shipList.add(battleship);
        shipList.add(cruiser);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Salvo");
        primaryStage.setScene(new Scene(root, 1000, 400));

        playerGrid = (GridPane) primaryStage.getScene().lookup("#playerGrid");
        GridPane opponentGrid = (GridPane) primaryStage.getScene().lookup("#opponentGrid");
        HBox boatBox = (HBox) primaryStage.getScene().lookup("#boatColumn");


        int count = 1;

        for (int x = 0; x < playerGrid.getRowCount(); x++){
            for (int y = 0; y < playerGrid.getColumnCount(); y++){
                Button button = createButton(count);
                Button opbutton = opButton(count);
                playerGrid.add(button, x, y);
                opponentGrid.add(opbutton, x, y);
                count++;
            }
        }

        myList = playerGrid.getChildren();
        primaryStage.show();


        final int httpd = 8081;

        Socket sock = new Socket("localhost", httpd);
        FromServer fromserver = new FromServer(sock);
        ToServer   toserver = new ToServer(sock, opponentGrid.getChildren());
        new Thread(toserver).start();
        new Thread(fromserver).start();
    }

    private Button createButton(int count) {
        Button button = new Button();
        button.setText("" + count);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> FillBoat(count));
        return button;
    }

    private Button opButton(int count){
        Button button = new Button();
        button.setText("" + count);
        button.setId("" + count);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }


    public static void main(String[] args) throws Exception { launch(args); }

    // Places the boat on the players grid if a valid placement
    public void FillBoat(int count){


        if(battleship.isVertical && !battleship.isPlaced){
            if(CompareRowsVert(count, (battleship.size + count)-1)){
                for (int x = 0; x < battleship.size; x++){
                    Button temp = (Button) myList.get(count + x);
                    temp.setText("X");
                }
                battleship.setPlaced(true);
            } else {
                System.out.println("Row selection not valid");
            }
        } else {
            //System.out.println("Horizontal");
        }
    }

    // Returns true if the row of the index and boatsize are in the same rows if the boat is vertical
    public boolean CompareRowsVert(int count, int boatSize){

        boolean match;

        if (count <= 5 && boatSize <= 5){
            match = true;
        } else if ((count <= 10 && count >= 6) && (boatSize <= 10 && boatSize >= 6)){
            match = true;
        } else if ((count <= 15 && count >= 11) && (boatSize <= 15 && boatSize >= 11)){
            match = true;
        }else if ((count <= 20 && count >= 16) && (boatSize <= 20 && boatSize >= 16)){
            match = true;
        }else if ((count <= 25 && count >= 21) && (boatSize <= 25 && boatSize >= 21)){
            match = true;
        }else
            match = false;

        return  match;
    }

    public void SendAttack(int count){
        System.out.println(count);
    }
}
