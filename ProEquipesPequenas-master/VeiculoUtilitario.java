public class VeiculoUtilitario extends Veiculo{

    public int capCargaTon;
    public int nroEixos;

    public VeiculoUtilitario(Placa placa, int capCargaTon, int nroEixos){
        super(placa);
        this.capCargaTon = capCargaTon;
        this.nroEixos = nroEixos;
    }

    public VeiculoUtilitario(Placa placa, int ano, String marca, Double combustivel, int capCargaTon, int nroEixos) {
        super(placa, ano, marca, combustivel);
        this.capCargaTon = capCargaTon;
        this.nroEixos = nroEixos;
    }

    public int getCapCargaTon() {
        return capCargaTon;
    }

    public void setCapCargaTon(int capCargaTon) {
        this.capCargaTon = capCargaTon;
    }

    public int getNroEixos() {
        return nroEixos;
    }

    public void setNroEixos(int nroEixos) {
        this.nroEixos = nroEixos;
    }

    @Override
    public String toString() {
        return "| Veiculo Utilitario |"+ super.toString() + "\nCap. de Carga em Toneladas: " + getCapCargaTon() + "\nNumero de eixos: " + nroEixos;
    }

    
}
