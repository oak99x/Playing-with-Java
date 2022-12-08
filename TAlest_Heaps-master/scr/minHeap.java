public class minHeap {

    private Acoes v[];
    private int used;

    public minHeap(int tam) {
        used = 0; // contador
        v = new Acoes[tam];
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        if (i == 0) {
            return 0;
        }
        return (i - 1) / 2;
    }

    public int getSize() {
        return used;
    }

    // trocar valores em dois índices
    private void swap(int pos, int posParent) {
        // faz a trocar
        Acoes aux = v[posParent];
        v[posParent] = v[pos];
        v[pos] = aux;
    }

    private void sift_up(int pos) {
        int posParent = parent(pos);

        // verifica se a pos no índice ‘i’ e seu pai não violam a propriedade heap
        if (pos > 0 && v[posParent].getPreco() > v[pos].getPreco()) { // --

            // troca os dois se a propriedade do heap for violada
            swap(pos, posParent);
            sift_up(posParent);
        }
    }

    public void put(Acoes acao) {

        v[used] = acao;
        sift_up(used);
        used++;
    }

    //Procedimento de desmontagem de heap
    private void sift_down(int pos) {
        int posLeft = left(pos);
        int posRight = right(pos);

        int menorPos = pos;
        Acoes menor = v[pos];

        //verifica se o índice é valido 
        // compara ‘menor’ com seus filhos esquerdo e direito e encontra o menor valor
        if (posLeft < this.used && menor.getPreco() > v[posLeft].getPreco()) {
            menorPos = posLeft;
            menor = v[posLeft];
        }

        if (posRight < this.used && menor.getPreco() > v[posRight].getPreco()) {
            menorPos = posRight;
            menor = v[posRight];
        }

        // troca com um filho de menor valor
        if (menorPos != pos) {
            swap(pos, menorPos);
            // chama sift_down no filho
            sift_down(menorPos);
        }
    }

    public Acoes get() {

        Acoes res = v[0];
        v[0] = v[--used];
        sift_down(0);
        return res;
    }

    private int len(int a) {
        int res = 0;
        while (a > 0) {
            res++;
            a /= 10;
        }
        return res;
    }

    public void print() {

        for (int i = 0; i < used; i++) {
            System.out.println(v[i]);
        }
    }

}