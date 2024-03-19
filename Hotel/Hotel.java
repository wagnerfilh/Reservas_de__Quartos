package Hotel;
import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private String name;
    // Constructor
    public Hotel(String name) {
        this.name = name;
    }

    // Getters and Setters for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
  public void addRoom(Room room){
    this.rooms.add(room);
  }
  
  public void removeRoom(Room specifRoom){
    for(Room room : rooms){
      if(specifRoom.getName().equals(room.getName())){
        this.rooms.remove(room);
        break;
      }
    }
  }
  public boolean isRoomOccupied(Room specifRoom){
    for(Room room : rooms){
      if(specifRoom.getName().equals(room.getName())){
        return room.isOccupied();
      }
    }
    return false;
  }
}