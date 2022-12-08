import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Treinamento {

    private List<Node> obstaculosOriginal;
    private Map<Node, List<Node>> grafoOriginal;

    private class Node {
        String nome;
        int tempo;
        int dependencias;

        public Node(String s, int t, int d) {

            nome = s;
            tempo = t;
            dependencias = d;
        }

        public String getNome() {
            return nome;
        }

        public int getTempo() {
            return tempo;
        }

        public void setTempo(int tempo) {
            this.tempo = tempo;
        }

        public int getDependencias() {
            return dependencias;
        }

        public void setDependencias(int dependencias) {
            this.dependencias = dependencias;
        }

        @Override
        public String toString() {
            return nome + " : " + tempo + " : " + dependencias;
        }
    }

    public Treinamento() {

        obstaculosOriginal = new ArrayList<>();
        grafoOriginal = new HashMap<>();

    }

    public void addNode(String nome, int tempo, int dependencias) {

        Node obstaculo = new Node(nome, tempo, dependencias);

        boolean verifica = false;
        for (Node e : obstaculosOriginal) {
            if (e.getNome().equals(nome)) {
                verifica = true;
                break;
            }
        }
        if (verifica == false) {
            obstaculosOriginal.add(obstaculo);
        }
    }

    public void addDependencia(String nome) {

        for (Node e : obstaculosOriginal) {
            if (e.getNome().equals(nome)) {
                e.setDependencias(e.getDependencias() + 1);
                break;
            }
        }

    }

    public void addAresta(String nome1, String nome2) {

        Node obstaculoA = new Node(null, 0, 0);
        Node obstaculoB = new Node(null, 0, 0);
        ;

        for (Node a : obstaculosOriginal) {
            if (a.getNome().equals(nome1)) {
                obstaculoA = a;
                break;
            }
        }

        for (Node b : obstaculosOriginal) {
            if (b.getNome().equals(nome2)) {
                obstaculoB = b;
                break;
            }
        }

        if (grafoOriginal.get(obstaculoA) == null) {
            grafoOriginal.put(obstaculoA, new ArrayList<Node>());
            grafoOriginal.get(obstaculoA).add(obstaculoB);

        } else {
            grafoOriginal.get(obstaculoA).add(obstaculoB);
        }

    }

    private int treinamentoOn(List<Node> n, Map<Node, List<Node>> a, int workers) {

        List<Node> disponiveis = new ArrayList<>();
        List<Node> fazendo = new ArrayList<>();

        // Cria-se um nó inicial para englobar todos os obstaculos, pois
        // nem todos os obstaculos com dependencia = 0 seriam vistos 
        // Adiciona-se um novo nó
        Node root = new Node(" ", 0, 0);
        int tempo = 0;

        //Faz a relação do novo nó para todos os obstaculos que tem dependencia = 0
        for (Node e : n) {

            if (e.getDependencias() == 0) {
                if (a.get(root) == null) {
                    a.put(root, new ArrayList<Node>());
                    a.get(root).add(e);

                } else {
                    a.get(root).add(e);
                }

                e.setDependencias(e.getDependencias() + 1);
            }
        }

        fazendo.add(root);

        // Enquanto a lista de disponiveis ou a lista fazendo
        // não forem vazias continua a percorrer o grafo
        while (!disponiveis.isEmpty() || !fazendo.isEmpty()) {

            //Procura-se o obstaculo com menor tempo, pois, se existem vários
            // obstáculos disponíveis para desmanche, o minion é enviado para 
            // o que vem primeiro em ordem alfabética, mas não é dito que precisa
            // ser entregue na mesma ordem.
            Node menorNodo = fazendo.get(0);

            for (Node e : fazendo) {

                if (e.getTempo() < menorNodo.getTempo()) {
                    menorNodo = e;
                }
            }

            tempo += menorNodo.getTempo();
            fazendo.remove(menorNodo);

            //percorre-se o grafo decrementando as dependências que o obstáculo removido
            //tinha sobre outros obstáculos. O obstáculo que ficar com suas dependências
            // igual a 0, é adicionado na lista de disponíveis para desmanche
            boolean flag = true;
            for (Node e : fazendo) {
                int t = e.tempo - menorNodo.getTempo();
                e.setTempo(t);
                if (e.tempo == 0) {
                    flag = false;
                }
            }

            // Após o tempo ser calculado, percorre-se o grafo decrementando as
            // dependências que o obstáculo removido tinha sobre outros obstáculos.
            for (Node e : n) {

                if (a.get(menorNodo) != null) {

                    if (a.get(menorNodo).contains(e)) {

                        e.setDependencias(e.getDependencias() - 1);

                        //O obstáculo que ficar com suas dependências igual a 0, 
                        //é adicionado na lista de disponíveis para desmanche.
                        if (e.getDependencias() == 0) {
                            disponiveis.add(e);
                        }
                    }
                }
            }

            // classe alinhada anonima para ordenação
            // passa-se a lista de disponíveis por uma ordenação por ordem alfabética garantindo
            // assim a condição de que se existem vários obstáculos disponíveis para desmanche
            disponiveis.sort(new Comparator<Node>() {

                @Override
                public int compare(Node n1, Node n2) {
                    return n1.getNome().compareTo(n2.getNome());
                }
            });

            while (fazendo.size() < workers && !disponiveis.isEmpty() && flag) {

                Node c = disponiveis.get(0);
                disponiveis.remove(0);
                fazendo.add(c);
            }
            // Imprimi na ordem que está sendo feita, a ordem correta de desmanche
            // for (Node e : fazendo) {
            // System.out.println(e);
            // }

        }
        return tempo;
    }

    private void copy(List<Node> obstaculosCopy, Map<Node, List<Node>> grafoCopy) {

        for (Node original : obstaculosOriginal) {
            Node obstaculoCopy = new Node(original.getNome(), original.getTempo(), original.dependencias);
            obstaculosCopy.add(obstaculoCopy);
        }

        Set<Node> chaves = grafoOriginal.keySet();
        for (Node chave : chaves) {

            Node obstaculoA = new Node(null, 0, 0);

            for (Node a : obstaculosCopy) {
                if (a.getNome().equals(chave.getNome())) {
                    obstaculoA = a;
                    break;
                }
            }

            List<Node> valoresKey = grafoOriginal.get(chave);
            for (Node v : valoresKey) {

                Node obstaculoB = new Node(null, 0, 0);

                for (Node b : obstaculosCopy) {
                    if (b.getNome().equals(v.getNome())) {
                        obstaculoB = b;
                        break;
                    }
                }

                if (grafoCopy.get(obstaculoA) == null) {
                    grafoCopy.put(obstaculoA, new ArrayList<Node>());
                    grafoCopy.get(obstaculoA).add(obstaculoB);

                } else {
                    grafoCopy.get(obstaculoA).add(obstaculoB);
                }
            }
        }
    }

    public void idealMinions(String caso) {

        int t = 0;
        int auxM = 0;
        int auxT = 10000000;

        for (int j = 1; j < 101; j++) {

            List<Node> obstaculosCopy = new ArrayList<>();
            Map<Node, List<Node>> grafoCopy = new HashMap<>();

            copy(obstaculosCopy, grafoCopy);

            t = treinamentoOn(obstaculosCopy, grafoCopy, j);

            //número ideal de minions para obter o menor tempo
            // if(t < auxT){
            // auxM = j;
            // auxT = t;
            // }
  
            // // número ideal de minions para ter o melhor aproveitamento no treinamento.
            if (t < auxT) {
                auxM = j;
                auxT = t;
            } else {
                break;
            }

        }

        System.out.println(caso);
        System.out.println("USANDO " + auxM + " || " + "TEMPO: " + auxT);
        System.out.println("=======================================================");

    }
}
