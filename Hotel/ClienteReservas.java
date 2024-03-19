package Hotel;
import java.rmi.Naming;
import java.util.Scanner;
public class ClienteReservas {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            ClienteInterface cli = (ClienteInterface) Naming.lookup("rmi://localhost/ClienteInterface");
            System.out.print("Bem vindo ao Hotel X, 11 para fazer o seu login ou 22 para cadastrar.");
            int numero = scanner.nextInt();
            int continuar = 0;
            while(continuar == 0){}
            switch (numero) {
                case 11:
                    String login = cli.cliente(numero);
                    continuar = 1;
                case 22:
                    String cadastro = cli.cliente(numero);
                    System.exit(0); 
                default:
                    System.out.println("Digite um número válido"); 
            }do{
            System.out.println("Vamos lá! o que podemos fazer por você hoje?");
            System.out.println("Digite 1 para fazer uma reserva, 2 para consultar uma reserva, 3 para modificar uma reserva, 4 para cancelar reservas ou -1 para sair:");
            switch (numero) {
                case 1:
                    String result = cli.cliente(numero);
                case -1:
                    System.out.println("Você deslogou.");
                    System.exit(0); 
                default:
                    System.out.println("Digite um número válido");
            }}while(numero != -1);
            // Interagir com o usuário para consulta, reserva, modificação e cancelamento de reservas

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
