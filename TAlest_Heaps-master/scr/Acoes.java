public class Acoes {
    private String c;
    private int quant;
    private int preco;

    public Acoes(String c, int quant, int preco) {
        this.c = c;
        this.quant = quant;
        this.preco = preco;
    }

    public String getChar() {
        return c;
    }

    public int getQuant() {
        return quant;
    }

    public int getPreco() {
        return preco;
    }    

    //atualiza quantidade caso não negocie todas as acões naquele momento
    public void setQuant(int quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return c + " : " + quant + " : " +preco;
    }
    
}

