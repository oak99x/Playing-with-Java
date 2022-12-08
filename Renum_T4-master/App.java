import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Renum renum = new Renum();

        char escolha = 's';
        while (escolha == 's') {
            System.out.println("Digite o nome do arquivo que voce deseja renumerar: ");
            System.out.println("1-Prog1.bas \n2-Prog2.bas");
            String opcao = in.next();
            System.out.println("\n");

            switch (opcao) {
                case "1":
                    renum.loadProgram("Prog1.bas");
                    renum.renumberFile("RenumProg1.bas");
                    break;
                case "2":
                    renum.loadProgram("Prog2.bas");
                    renum.renumberFile("RenumProg2.bas");
                    break;
                default:
                    System.out.println("Invalid");
                    break;
            }

            System.out.println("Deseja renumerar outro arquivo? sim / nao");
            escolha = in.next().charAt(0);
            System.out.println("-------------------------------------------------\n");
        }

    }

}
