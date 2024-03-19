package Hotel;
public class Reservation{
    private Room room;
    private boolean active;

    // Constructor
    public Reservation(Room room, boolean active) {
        this.room = room;
        this.active = active;
    }

    // Getters and Setters
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room)  {
      if (!room.isOccupied()) {
        System.out.println("Reserva não esta ativa");
      }
      else{ this.room = room; }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}