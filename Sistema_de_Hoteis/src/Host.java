import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;

import interfaces.IServidor;

public class Host {
    private static IServidor servidor;

    public static void main(String[] args) {
        try {
            Registry registroHost = LocateRegistry.getRegistry("localhost", 1099);
            servidor = (IServidor) registroHost.lookup("Server");

            Scanner scanner = new Scanner(System.in);

            boolean continuar = true;
            final String menu = """
                    1 - Fazer Reserva
                    2 - Atualizar Reserva
                    3 - Cancelar Reserva
                    4 - Sair do sistema
                    """;
            final String msgErro = "ERRO, OPÇÃO INVÁLIDA, TENTE NOVAMENTE";
            System.out.println("Bem-vindo ao sistema de reservas de hotéis!");
            while (continuar) {
                System.out.println(menu);
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (escolha) {
                    case 1:
                        addReserva(scanner);
                        break;
                    case 2:
                        attReserva(scanner);
                        break;
                    case 3:
                        delReserva(scanner);
                        break;
                    case 4:
                        System.out.println("Saindo do sistema...");
                        continuar = false;
                        break;
                    default:
                        System.out.println(msgErro);
                        break;
                }
            }
            System.exit(0); // terminando o servidor
        } catch (Exception e) { e.printStackTrace(); }
    }

    // colocamos um Scanner de parametro para evitar a criação de objetos toda vez que o método for chamado
    private static void addReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do quarto: ");
        int numeroQuarto = scanner.nextInt();
        String resultado = servidor.addReserva(nomeDoHotel, numeroQuarto);
        System.out.println(resultado);
    }

    private static void attReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do quarto atual da reserva: ");
        int quartoAtual = scanner.nextInt();
        System.out.println("Digite o número do novo quarto da reserva:");
        int quartoNovo = scanner.nextInt();

        String resultado = servidor.attReserva(nomeDoHotel, quartoAtual, quartoNovo);
        System.out.println(resultado);
    }

    private static void delReserva(Scanner scanner) throws Exception {
        System.out.println("Digite o nome do hotel:");
        String nomeDoHotel = scanner.nextLine();
        System.out.println("Digite o número do Quarto");
        int numeroQuarto = scanner.nextInt();
        String resultado = servidor.delReserva(nomeDoHotel, numeroQuarto);
        System.out.println(resultado);
    }
}
