package sample;

public class Boat {

    int health;
    int size;
    String type;
    boolean isVertical = true;
    boolean isPlaced = false;

    public Boat(){}

    public Boat(int health, int size, String type, boolean isVertical){
        this.health = health;
        this.size = size;
        this.type = type;
        this.isVertical = isVertical;
        isPlaced = false;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public void setVertical() {
        isVertical = !this.isVertical;
    }

    public int getHealth() {
        return health;
    }
}
