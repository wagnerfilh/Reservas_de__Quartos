package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidor extends Remote {
    String addReserva(String hotel, int numeroHotel) throws RemoteException;

    String attReserva(String nomeHotel, int quartoAtual, int novoQuarto) throws RemoteException;

    String delReserva(String nomeHotel, int numeroQuarto) throws RemoteException;
}
