public class Veiculo{
    private final double consumoPorLitro = 10;
    private Placa placa;
    private double combustivel;
    private int ano;
    private String marca;
    
    public Veiculo(Placa placa){
        this.placa = placa;
        this.combustivel = 0;
        this.ano = 0;
        this.marca = "";
    }

    public Veiculo(Placa placa, int ano, String marca, double combustivel) {
        this.placa = placa;
        this.combustivel = combustivel;
        this.ano = ano;
        this.marca = marca;
    }

    

    public void setPais(String pais){
        placa.setPais(pais);
    }

    public Placa getPlaca(){
        return placa;
    }

    public double getCombustivelNoTanque(){
        return combustivel;
    }

    public String getMarca(){
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double abastece(double litros){
        if (litros > 0.0){
            combustivel += litros;
        }
        return combustivel;
    }

    // Simula o deslocamento do carro por uma determinada distancia em Km:
    //  - Diminui a quantidade de combustivel gasto do tanque
    //  - Retorna a distancia efetivamente percorrida (com o combustivel disponivel)
    public double dirige(double distancia){
        // Para distancias impossíveis retorna 0
        if (distancia <= 0){
            return 0.0;
        }
        // Calcula o consumo para a distancia
        double consumo = distancia / consumoPorLitro;
        // Diminui o combustivel gasto e retorna a distancia percorrida
        if (combustivel >= consumo){
            combustivel -= consumo;
            return distancia;
        }else{
            double distPossivel = combustivel * consumoPorLitro;
            combustivel = 0.0;
            return distPossivel;
        }
    }

    public String toString(){
        return "\nPlaca: "+ getPlaca() +"\nCombustivel no tanque: "+getCombustivelNoTanque() + "\nAno: " + getAno() + "\nMarca: " + getMarca();
    }
}