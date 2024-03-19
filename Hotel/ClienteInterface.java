package Hotel;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteInterface extends Remote {
    String cliente(int requerimento) throws RemoteException;
}