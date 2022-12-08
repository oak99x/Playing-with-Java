public class maxHeap {

    private Acoes v[];
    private int used;

    public maxHeap(int tam) {
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
        if (pos > 0 && v[posParent].getPreco() < v[pos].getPreco()) { // --

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

    private void sift_down(int pos) {
        int posLeft = left(pos);
        int posRight = right(pos);

        int maiorPos = pos;
        Acoes maior = v[pos];

        //verifica se o índice é valido 
        // compara 'maior'  com seus filhos esquerdo e direito e encontra o maior valor
        if (posLeft < this.used && maior.getPreco() < v[posLeft].getPreco()) {
            maiorPos = posLeft;
            maior = v[posLeft];
        }

        if (posRight < this.used && maior.getPreco() < v[posRight].getPreco()) {
            maiorPos = posRight;
            maior = v[posRight];
        }

        // troca com um filho de maior valor
        if (maiorPos != pos) {
            swap(pos, maiorPos);
            // chama sift_down no filho
            sift_down(maiorPos);
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
            System.out.println(v[i].getPreco());
        }
    }

}