import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoVeiculos {

    private static List<Veiculo> lista = new ArrayList<>();

    public static void preencheLista(){
        Placa placa = new Placa("BR", "XXX0X00");
        Placa p = new Placa("brasil", "AAA1A11");
        Placa p1 = new Placa("colombia", "CVA1A11");
        Placa p2 = new Placa("dinamarca", "BBA1A11");
        Placa p3 = new Placa("brasil", "AKE1A11");
        Placa p4 = new Placa("brasil", "KLL1A11");
        Veiculo v1 = new Veiculo(p);
        Veiculo v2 = new Veiculo(p1);
        VeiculoPassageiros vp1 = new VeiculoPassageiros(p3, 5);
        VeiculoPasseio vpa1 = new VeiculoPasseio(p4, 23.0);
        lista.add(v1);
        lista.add(v2);
        lista.add(vp1);
        lista.add(vpa1);
        lista.add(new Veiculo(new Placa("ucrania", "DDS9F876")));
        lista.add(new Veiculo(new Placa("brasil", "DXX9F876")));
        lista.add(new Veiculo(new Placa("brasil", "DDS9F879")));
        lista.add(new Veiculo(new Placa("uruguay", "DDS9F880")));
        lista.add(new Veiculo(new Placa("argentina", "DSS9F876")));
        lista.add(new Veiculo(new Placa("brasil", "AAE9F876")));
    }

    public String registraVeiculo(Veiculo veiculo) {

        //verificar se já existe
        if (veiculo.getPlaca().getCodigo().equals("AAA0A00")){
            return "-- Placa Invalida --";
        }
                
        
        for (Veiculo v : lista) {
            if (v.getPlaca().getCodigo().equals(veiculo.getPlaca().getCodigo())){
                return "-- Veiculo já cadastrado --";
            }      
        }
        
        lista.add(veiculo);
        return "-- Veiculo Registrado --";
    }

    public Veiculo consultaPorPlaca(String placa) {
        Veiculo veiculo = null;

        for (Veiculo v : lista) {
            if (v.getPlaca().getCodigo().equals(placa))
                return v;
        }

        return veiculo;
    }

    public List<Veiculo> consultaPorMarca(String marca) {
        List<Veiculo> veiculo = new ArrayList<>();

        for (Veiculo v : lista) {
            if (v.getMarca().equals(marca))
            veiculo.add(v);
        }

        return veiculo;
    }

    public List<Veiculo> consultaPorAno(int ano) {
        List<Veiculo> veiculo = new ArrayList<>();

        veiculo = lista.stream()
                .filter(v -> v != null)
                .filter(v -> v.getAno() == ano)
                .collect(Collectors.toList());

        return veiculo;
    }

    public List<Veiculo> consultaPorTipo(String tipo) {
        List<Veiculo> veiculo = new ArrayList<>();

        switch (tipo) {
            case "VEICULO DE PASSAGEIROS":
                for (Veiculo o : lista)
                    if (o instanceof VeiculoPassageiros)
                    veiculo.add(o);
                break;

            case "VEICULO DE PASSEIO":
                for (Veiculo o : lista)
                    if (o instanceof VeiculoPasseio)
                    veiculo.add(o);
                break;

            case "VEICULO UTILITARIO":
                for (Veiculo o : lista)
                    if (o instanceof VeiculoUtilitario)
                    veiculo.add(o);
                break;
            default:
                System.out.println("Nada encontrado :(");
                veiculo = null;
                break;
        }

        return veiculo;
    }
}
