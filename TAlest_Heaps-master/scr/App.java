import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static minHeap vendaHeap; // min heap - menor é raiz
    public static maxHeap compraHeap; // max heap - maior é raiz
    public static long lucro;
    public static int aNegociadas;
    public static Acoes ac;
    public static int i = 0;

    public static void main(String[] args) {
        int caso[] = { 30, 10, 100, 1000, 10000, 100000, 1000000, 10000000 };

        for (int i = 0; i < 8; i++) {
            long start = System.currentTimeMillis();

            vendaHeap = new minHeap(caso[i]);
            compraHeap = new maxHeap(caso[i]);
            lucro = 0;
            aNegociadas = 0;
            leitura(caso[i]);
            
            long tempoFinal = System.currentTimeMillis();
            System.out.printf("Tempo médio: %.4f s%n", (tempoFinal - start) / 1000d);
            System.out.println("-------------------------------------------------\n");
           

        }

    }

    public static void leitura(int caso) {

        // leitura dos arquivos
        String aux[];
        Path path1 = Paths.get("caso" + caso + ".txt");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                aux = line.split(" ");

                //cria uma Acao a partir do split da linha lida
                //ex ->   aux[0]= C     aux[0] = 123    aux[3] = 1362
                ac = new Acoes(aux[0], Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));

                //verifica para qual heap vai ser lançado
                if (aux[0].equals("C")) {
                    compraHeap.put(ac);
                } else {
                    vendaHeap.put(ac);
                }

                // a cada ação que entrar, chama o método para calcular as negociações possíveis
	            // só é possível começar uma possível negociação se já tiver conteúdo nos 2 heaps 
                if (compraHeap.getSize() > 0 && vendaHeap.getSize() > 0) {
                    melhorCaso(compraHeap.get(), vendaHeap.get());
                }
        
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        //Parte de impressão dos resultados obtidos
        // A quantidade de ações negociadas;
        System.out.println("Caso: " + caso);

        // A quantidade de ações negociadas;
        System.out.println("A quantidade de ações negociadas: " + aNegociadas);

        // Lucro da empresa;
        System.out.println("Lucro da empresa: $" + lucro);

        // O tamanho da lista de compras ainda pendentes;
        System.out.println("Compras ainda pendentes: " + compraHeap.getSize());

        // O tamanho da lista de vendas ainda pendentes;
        System.out.println("Vendas ainda pendentes: " + vendaHeap.getSize());

       
    }

    public static void melhorCaso(Acoes compra, Acoes venda) {

        // observe que o método já entra com uma compra e uma venda em seus parâmetros 
        // esse ‘get’ se dá quando ele é chamado dentro do método leitura.
        // verifica se da para negocia
        if (compra.getPreco() >= venda.getPreco()) {

            if (compra.getQuant() >= venda.getQuant()) {
                // se ainda sobrar acoes para compra atualiza a quantidade e volta para a lista
                int volta = compra.getQuant() - venda.getQuant();
                if (volta != 0) {
                    compra.setQuant(volta);
                    compraHeap.put(compra);
                }
                // incrementa o lucro e a quantidade de acoes negociadas;
                lucro += (compra.getPreco() - venda.getPreco()) * (venda.getQuant());
                aNegociadas += venda.getQuant();

            } else {
                // se ainda sobrar acoes para venda atualiza a quantidade e volta para a lista
                int volta = venda.getQuant() - compra.getQuant();
                if (volta != 0) {
                    venda.setQuant(volta);
                    vendaHeap.put(venda);
                }
                // incrementa o lucro e a quantidade de acoes negociadas;
                lucro += (compra.getPreco() - venda.getPreco()) * (compra.getQuant());
                aNegociadas += compra.getQuant();

            }

            // chama recursivamente verificando se nao surgiu outra 
            // oportunidade de negociacao
            if (compraHeap.getSize() > 0 && vendaHeap.getSize() > 0) {
                melhorCaso(compraHeap.get(), vendaHeap.get());
            }

        } else {
            // se nao da para negociar coloca novamente cada um em sua lista
            compraHeap.put(compra);
            vendaHeap.put(venda);
        }

    }
}