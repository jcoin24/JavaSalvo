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
    static List<Boat> shipList = new ArrayList<Boat>();
    static Boat currentBoat;


    // Declare playerGrid variable
    GridPane playerGrid;

    @Override
    public void start(Stage primaryStage) throws Exception{

        shipList.add(battleship);
        shipList.add(cruiser);
        currentBoat = shipList.get(0);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Salvo");
        primaryStage.setScene(new Scene(root, 1000, 400));

        playerGrid = (GridPane) primaryStage.getScene().lookup("#playerGrid");
        GridPane opponentGrid = (GridPane) primaryStage.getScene().lookup("#opponentGrid");
        HBox boatBox = (HBox) primaryStage.getScene().lookup("#boatColumn");
        Button readyButton = (Button) primaryStage.getScene().lookup("#readyButton");



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
        // Check if the boat is placed and if not to place it vertically
        if(currentBoat.isVertical && !currentBoat.isPlaced){
            // Check if the row placement is valid or not
            if(CompareRowsVert(count, (currentBoat.size + count)-1)){

                // Place either a + or a * for the type of boat selected
                int[] locations = new int[currentBoat.size];
                boolean valid = false;

                // Iterate through the location to be placed and see if it is valid
                for (int x = 0; x < currentBoat.size; x++){
                    locations[x] = count + x;
                    Button temp = (Button) myList.get(count + x);
                    try{
                        Integer.parseInt(temp.getText());
                        valid = true;
                    }catch (Exception e){
                        valid = false;
                        System.out.println("Ship already exist there");
                        break;
                    }
                }

                if(valid){
                    currentBoat.setPlaced(true);
                    for (int x = 0; x < locations.length; x++){
                        Button temp = (Button) myList.get(locations[x]);

                        if(currentBoat.type.compareTo("Battleship") == 0){
                            temp.setText("+");
                        }else
                            temp.setText("*");
                    }
                }

            } else {
                System.out.println("Row selection not valid");
            }

            // Check if the boat is horizontal and can be placed
        } else if(!currentBoat.isVertical && !currentBoat.isPlaced){

            //Check if the boat can be placed horizontally
            if((currentBoat.size + 7 + count) < myList.size()){

                // Place either a + or a * for the type of boat selected
                int tempLocation = 0;
                int[] locations = new int[currentBoat.size];
                boolean valid = false;

                // Iterate through the location to be placed and see if it is valid
                for (int x = 0; x < currentBoat.size; x++){
                    locations[x] = count + x + tempLocation;
                    Button temp = (Button) myList.get(count + x + tempLocation);
                    tempLocation += 4;
                    try{
                        Integer.parseInt(temp.getText());
                        valid = true;
                    }catch (Exception e){
                        valid = false;
                        System.out.println("Ship already exist there");
                        break;
                    }
                }

                if(valid){
                    currentBoat.setPlaced(true);
                    for (int x = 0; x < locations.length; x++){
                        Button temp = (Button) myList.get(locations[x]);

                        if(currentBoat.type.compareTo("Battleship") == 0){
                            temp.setText("+");
                        }else
                            temp.setText("*");
                    }
                }
            }else
                System.out.println("Invalid vertical placement");
        }else
            System.out.println("Error invalid placement");
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

    public static void setCurrentBoat(int num){
        currentBoat = shipList.get(num);
    }

    public static void SetCurrentBoatHorizontal(){
        currentBoat.setVertical();
    }

    public void SendAttack(int count){
        System.out.println(count);
    }
}
