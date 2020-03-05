package sample;

public class Boat {

    int health;
    int size;
    String type;
    boolean isVertical = true;

    public Boat(){}

    public Boat(int health, int size, String type, boolean isVertical){
        this.health = health;
        this.size = size;
        this.type = type;
        this.isVertical = isVertical;
    }
}
