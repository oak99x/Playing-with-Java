import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Leitura {

    private Treinamento campoDeTreino; 

    public Leitura() {

        campoDeTreino = new Treinamento();
        
    }


    public void leitura(String caso) {

        String word[];
        Path path1 = Paths.get(caso);

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                word = line.split(" -> ");

                String aux1[] = word[0].split("_");
                String aux2[] = word[1].split("_");

                //Cria o nó (obstaculo)
                campoDeTreino.addNode(aux1[0], Integer.parseInt(aux1[1]), 0);
                campoDeTreino.addNode(aux2[0], Integer.parseInt(aux2[1]), 0);
                //Incrementa dependencias
                campoDeTreino.addDependencia(aux2[0]);
                //Adiciona relacao no grafo
                campoDeTreino.addAresta(aux1[0],aux2[0]);

            }

            //Apos a leitura chama idealMinions(caso) para os testes
            campoDeTreino.idealMinions(caso);
            
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

    }
}
