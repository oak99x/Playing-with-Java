public class VeiculoPassageiros extends Veiculo{
    private int  nroPass;

    public VeiculoPassageiros(Placa placa, int nroPass) {
        super(placa);
    }

    public VeiculoPassageiros(Placa placa, int ano, String marca, Double combustivel, int nroPass) {
        super(placa, ano, marca, combustivel);
        this.nroPass = nroPass;
    }

    public int getNroPass() {
        return nroPass;
    }

    public void setNroPass(int nroPass) {
        this.nroPass = nroPass;
    }

    @Override
    public String toString() {
        return "| Veiculo de Passageiros |"+ super.toString() + "\nNumero de passageiros: " + getNroPass();
    }    
}