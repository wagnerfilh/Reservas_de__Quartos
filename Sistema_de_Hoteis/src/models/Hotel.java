package models;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nome;
    private List<Quarto> quartos;
    private List<Reservas> reservas = new ArrayList<Reservas>();

    public Hotel(String nome, List<Quarto> quartos) {
        this.nome = nome;
        this.quartos = quartos;
    }

    public String getNome() {
        return nome;
    }
    public List<Quarto> getQuartos() {
        return this.quartos;
    }

    public String listarReservas(){
        StringBuilder sb = new StringBuilder();
        for(Reservas res : reservas){
            sb.append(res.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}