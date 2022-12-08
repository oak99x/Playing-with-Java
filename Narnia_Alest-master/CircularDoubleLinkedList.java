public class CircularDoubleLinkedList {
    /*
    1.  inicio = ponto de partida *vai ser sempre o primeiro elemento a ser inserido
    2.  elem_Ativo serviu tanto como header como trailer assim fechando o circulo de
        forma mais simplificada
    3.  No get_Vizinho, para esta versão, apos o 21º elemento introduzido todo elemento sera do tipo Homem_livre,
    4.  Custo do algoritmo eh O(n^2), ou seja, eh quadratico
    */

    public int contOp; // contador de operações
    private int count;
    private Node inicio;
    private Node elem_Ativo; // header trailer

    private class Node {
        public Integer element;
        public Node next;
        public Node prev;

        public Node(Integer e) {
            element = e;
            next = null;
            prev = null;
        }
    }

    public CircularDoubleLinkedList() {

        inicio = new Node(null);
        elem_Ativo = new Node(null);
        count = 0;
        contOp = 0;
    }

    // Notacao O(n^2)
    public void add(Integer element) {

        Node n = new Node(element);

        // se alista estiver vazia o primeiro elemento vai apontar para ele mesmo.
        if (isEmpty()) {

            n.next = n;
            n.prev = n;

            elem_Ativo = n;
            inicio = n;

            count++;

            contOp++;
        } else {

            Node aux = elem_Ativo;

            // se a soma dos vizinhos for igual a 0, add na ordem normal
            if ((aux.prev.element + aux.next.element) == 0) {

                n.next = aux.prev;
                n.prev = aux;
                aux.next.prev = n;
                aux.next = n;

                elem_Ativo = n;
                count++;

                contOp++;

            } else {
                // se a soma for diferente de 0, guardar o resultado em uma variavel axiliar e
                // usa-se um for* para avancar ate posicao correspondente.


                // posicao apos soma dos vizinhos
                int pos = (aux.prev.element + aux.next.element);
                
                // se pos for >= count, isso significa que ele da uma volta inteira no elemento ativo
                // ou seja, otinizando pegamos o resto de pos-count e damos next a partir do elemento ativo
                if(pos>=count){

                    pos = pos-count;
                   
                    for (int i = 0; i < pos; i++) {
                        aux = aux.next;
                        contOp++;
                    }
                    
                }else{
                    for (int i = 0; i < pos; i++) {
                    aux = aux.next;
                    contOp++;
                }
            }   
                n.next = aux.next;
                n.prev = aux;
                aux.next.prev = n;
                aux.next = n;

                elem_Ativo = n;
                count++;
            }
        }
    }

    // Retorna os vizinhos tendo como entrada o elemento escolhido
    //Busca qualquer elemento e seus vizinhos
    public void getVizinhos(Integer element) {
        Node aux = null;
        int index = this.indexOf(element);

        aux = inicio;

        if (index <= count / 2) { // se esta na primeira metade da lista

            for (int i = 0; i < index; i++) {
                aux = aux.next;
                ;
            }

        } else { // se esta na segunda metade da lista

            for (int i = count - 1; i > index; i--)
                aux = aux.prev;
        }

        P_Reino p = new P_Reino();

        StringBuilder s = new StringBuilder();

            s.append("Os vizinhos do(a) " + p.getPessoa(aux.element) + "[" + aux.element + "]" + " sao: ");
            s.append("\n");
            s.append("À esquerda: " + p.getPessoa(aux.prev.element) + " [" + aux.prev.element + "] ");
            s.append("\n");
            s.append("À direita: " + p.getPessoa(aux.next.element) + " [" + aux.next.element + "] ");

        System.out.println(s);

    }

    public int indexOf(Integer element) {
        Node aux = inicio;
        for (int i = 0; i < count; i++) {
            if (aux.element.equals(element)) {
                return i;
            }
            aux = aux.next;
            contOp++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    // Retorna o elemento ativo
    public Integer getElem_Ativo() {
        return elem_Ativo.element;
    }

    public void clear() {
        inicio = new Node(null);
        elem_Ativo = new Node(null);
        count = 0;
        contOp = 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node aux = inicio;
        for (int i = 0; i < count; i++) {
            if (aux.equals(elem_Ativo)) {
                s.append("[" + aux.element.toString() + "]");
            } else {
                s.append(aux.element.toString());
            }

            if (!aux.equals(inicio.prev))
                s.append(",");

            aux = aux.next;
        }
        return s.toString();
    }

}