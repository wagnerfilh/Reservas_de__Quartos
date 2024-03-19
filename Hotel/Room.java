package Hotel;
public class Room{
    private String name;
    private boolean occupied;

    // Constructor
    public Room(String name, boolean occupied) {
        this.name = name;
        this.occupied = occupied;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
    