import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import interfaces.IServidor;

import models.Hotel;
import models.Quarto;

public class HotelServer extends UnicastRemoteObject implements IServidor {
    private List<Hotel> hoteis = new ArrayList<>();

    protected HotelServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            HotelServer servidorHotel = new HotelServer();
            Registry registroServer = LocateRegistry.createRegistry(1099);
            registroServer.rebind("Server", servidorHotel);
            System.out.println("Servidor Hotelaria iniciado");

            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            final String msgErro = "ERRO, OPÇÃO INVÁLIDA, TENTE NOVAMENTE";
            while (continuar) {
                System.out.println("""
                        Opções: 
                        1 - add Hotel
                        2 - Atualizar Reserva
                        3 - Remover Reserva
                        4 - Remover Hotel
                        5 - Sair do sistema
                        """); 
                int escolha = scanner.nextInt();
                // scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotel = scanner.nextLine();
                        
                        servidorHotel.addHotelQuartos(nomeHotel);
                        break;
                    case 2:
                        System.out.println("Digite o nome do hotel:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o número do quarto atual da reserva: ");
                        int quartoAtual = scanner.nextInt();
                        System.out.println("Digite o número do novo quarto da reserva:");
                        int quartoNovo = scanner.nextInt();

                        String resultado = servidorHotel.attReserva(nome, quartoAtual, quartoNovo);
                        System.out.println(resultado);
                        break;
                    case 3:
                        System.out.println("Digite o nome do hotel:");
                        String nomeHotelRemoverReserva = scanner.nextLine();
                        System.out.println("Digiteo número do quarto");
                        int numeroQuarto = scanner.nextInt();
                        String resultadoRemocao = servidorHotel.delReservaAdmin(nomeHotelRemoverReserva, numeroQuarto);
                        System.out.println(resultadoRemocao);
                        break;
                    case 4:
                        System.out.println("Digite o nome do hotel que deseja remover:");
                        String nomeHotelRemover = scanner.nextLine();
                        servidorHotel.delHotelAdmin(nomeHotelRemover);
                        break;
                    case 5:
                        System.out.println("Encerrando servidor...");
                        continuar = false;
                        break;
                    default:
                        System.out.println(msgErro);
                        break;
                }
            }
            scanner.close();
            System.exit(0);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void addHotelQuartos(String nomeHotel) throws RemoteException {
        try {
            List<Quarto> quartos = new ArrayList<>();
            int numeroQuarto;
            System.out.println("Agora informe que os quartos do hotel.");
            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;
            while (continuar) {
                System.out.println("Digite o número do quarto (-1 para sair):");
                numeroQuarto = scanner.nextInt();
                if (numeroQuarto == -1) continuar = false;
                else quartos.add(new Quarto(numeroQuarto));
            }
            scanner.nextLine();
            Hotel hotel = new Hotel(nomeHotel, quartos);
            hoteis.add(hotel);
            System.out.println("Hotel adicionado: " + nomeHotel);
            for (Quarto quarto : quartos) {
                System.out.println("Quarto adicionado: " + quarto.getNumero());
            }
            scanner.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void delHotelAdmin(String nomeHotel) {
        // utilizamos o iterator pois não é possivel deletar elementos de uma lista durante o foreach
        Iterator<Hotel> iterator = hoteis.iterator(); 
        while (iterator.hasNext()) {
            Hotel hotel = iterator.next();
            if (hotel.getNome().equals(nomeHotel)) {
                iterator.remove();
                System.out.println("Hotel removido: " + nomeHotel);
                return;
            }
        }
        System.out.println("Não há hoteis com esse nome no sistema");
    }


    private String delReservaAdmin(String nomeDoHotel, int numeroQuarto){
        for(Hotel hotel : hoteis){
            if(hotel.getNome().equals(nomeDoHotel)){
                for (Quarto quarto : hotel.getQuartos()){
                    if(quarto.getNumero() == numeroQuarto){
                        if(!quarto.isOcupado()){
                            quarto.setOcupado(true);
                            return "Reserva removida com sucesso.";
                        } else {
                            return "Não há reserva para esse quarto";
                        }
                    }
                }
            }
        }
        return "Hotel ou Reserva não encontrado.";
    }


    @Override
    public String addReserva(String hotel, int numeroQuarto) throws RemoteException {
        for(Hotel h : hoteis){
            if(h.getNome().equals(hotel)){
                for (var q : h.getQuartos()){
                    if(q.getNumero() == numeroQuarto){
                        if(q.isOcupado()){
                            q.setOcupado(false);
                            return "Reserva adicionada com sucesso.";
                        } else {
                            return "Não foi possível add a reserva.";
                        }
                    }
                }

            }
        }
        return "Quarto ou Hotel não encontrado.";
    }
    
    @Override
    public String attReserva(String nomeHotel, int quartoAtual, int novoQuarto) {
        for (Hotel hotel : hoteis) {
            if (hotel.getNome().equals(nomeHotel)) {
                for (Quarto quarto : hotel.getQuartos()) {
                    if (quarto.getNumero() == quartoAtual) {
                        if (!quarto.isOcupado()) {
                            for (Quarto novo : hotel.getQuartos()) {
                                if (novo.getNumero() == novoQuarto) {
                                    if (novo.isOcupado()) {
                                        quarto.setOcupado(true);
                                        novo.setOcupado(false);
                                        return "Reserva modificada com sucesso.";
                                    } else {
                                        return "O novo quarto já está reservado.";
                                    }
                                }
                            }
                            return "Novo quarto não encontrado.";
                        } else {
                            return "O quarto atual não está reservado.";
                        }
                    }
                }
                return "Quarto atual não encontrado ou indisponivel.";
            }
        }
        return "Hotel não encontrado.";
    }

    @Override
    public String delReserva(String nomeDoHotel, int numeroQuarto){
        for(Hotel h : hoteis){
            if(h.getNome().equals(nomeDoHotel)){
                for (var q : h.getQuartos()){
                    if(q.getNumero() == numeroQuarto){
                        if(!q.isOcupado()){
                            q.setOcupado(true);
                            return "Reserva removida com sucesso.";
                        } else {
                            return "Nenhuma reserva a ser removida";
                        }
                    }
                }
            }
        }
        return "Hotel ou Reserva não encontrado.";
    }
}