package models;
import java.util.UUID;

public class Reservas extends Quarto{

    private UUID uuid = null; //UUUID é um int positivo de 128b
    public Reservas(int id, boolean ocupado) {
        super(id, ocupado);
    }

    public UUID fazer_reserva(){
        if(!ocupado) return uuid;
        else{
            UUID idAleatorio = UUID.randomUUID();
            String reservaId = idAleatorio.toString();
            System.out.println("Reserva feita com sucesso! ID: " + reservaId);
            return idAleatorio;
        }
    }
}