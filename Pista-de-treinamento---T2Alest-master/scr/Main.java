
public class Main {
    

    public static void main(String[] args) throws Exception {

        String casos[] = { "caso0500.txt", "caso1000.txt", "caso1500.txt", "caso2000.txt", "caso2500.txt",
                           "caso3000.txt", "caso3500.txt", "caso4000.txt", "caso4500.txt", "caso5000.txt" };

        for (int i = 0; i < 10; i++) {
            // long start = System.currentTimeMillis();

            Leitura arquivo = new Leitura();
            arquivo.leitura(casos[i]);

            // long end = System.currentTimeMillis();
            // System.out.printf("Tempo execução: %.4f s%n", (end - start) / 1000d);
            // System.out.println("-------------------------------------------------\n");
        }
    }
}
