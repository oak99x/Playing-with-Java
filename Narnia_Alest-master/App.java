import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CircularDoubleLinkedList l = new CircularDoubleLinkedList();

        System.out.println("20 PRIMEIROS PASSO A PASSO\n");
       
        for (int i = 0; i <= 20; i++) {

            l.add(i);
            System.out.println(l +"         ELEMENTO ATIVO -> INDEX [" + l.indexOf(i) + "]");
    
            // System.out.println(i + ";" + l.contOp); //contador de operacoes
        }

        System.out.println("\n-----------------------------------------");

        System.out.println("** Vizinhos no circulo **");
        System.out.println("Caso com: 20 habitantes\n");
        l.getVizinhos(0); // Pode buscar qualquer vizinho

        System.out.println("-----------------------------------------\n");

        //TESTE PARA 100, 200, 300, 400 E 500 MIL HABITANTES/////////////////

        char aux = 's';
        while (aux=='s') {

            l.clear();
            System.out.println("Digite o numero de habitantes:");
            int h = in.nextInt();

            for (int i = 0; i <= h; i++) {
                l.add(i);
            }
            System.out.println("\n** Vizinhos no circulo **");
            System.out.println("Caso com: " + h + " habitantes\n");
            l.getVizinhos(0);
            //Poderia buscar qualquer elemento ao perguntar para o usuario, aqui no casso o Rei é fixo

            System.out.println("\nNova cosulta: sim/nao");
            aux = in.next().charAt(0);
            System.out.println("-----------------------------------------\n");

        }
    }   
}
