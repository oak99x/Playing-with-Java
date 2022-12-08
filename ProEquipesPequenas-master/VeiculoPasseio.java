public class VeiculoPasseio extends Veiculo {

    private Double consumo_km_litro;

    public VeiculoPasseio(Placa placa, Double consumo_km_litro) {
        super(placa);
        this.consumo_km_litro = consumo_km_litro;
    }

    public VeiculoPasseio(Placa placa, int ano, String marca, Double combustivel, Double consumo_km_litro) {
        super(placa, ano, marca, combustivel);
        this.consumo_km_litro = consumo_km_litro;
    }

    public Double getCombustivel() {
        return consumo_km_litro;
    }

    public void setCombustivel(Double combustivel) {
        this.consumo_km_litro = combustivel;
    }

    @Override
    public String toString() {
        return "| Veiculo de Passeio |" + super.toString() + "\nConsumo km_ por litro: " + getCombustivel();
    }

    
    
}
