import java.util.Scanner;

public class InterfaceTexto {

    Scanner ler = new Scanner(System.in);
    CatalogoVeiculos catalogo = new CatalogoVeiculos();

    

        

    public InterfaceTexto() {
        catalogo.preencheLista();
    }

    public void menu() {

        System.out.println(
                "MENU:"
                        + "\n1- Registrar Veiculo"
                        + "\n2- Consultar Por Placa"
                        + "\n3- Consulta Por Marca"
                        + "\n4- Consulta Por Ano"
                        + "\n5- Consulta Por Tipo"
                        + "\n6- Fechar");

        int opcao = ler.nextInt();

        switch (opcao) {
            case 1:
                registraVeiculoInterface();
                menu();
                break;
            case 2:
                consultaPorPlacaInterface();
                menu();
                break;
            case 3:
                consultaPorMarcaInterface();
                menu();
                break;
            case 4:
                consultaPorAnoInterface();
                menu();
                break;
            case 5:
                consultaPorTipoInterface();
                menu();
                break;
            case 6:
                break;
            default:
                System.out.println("Opcao Invalida");
                menu();
                break;
        }

    }

    private void registraVeiculoInterface() {

        System.out.println("Digite a Placa:  \n[formato AAA0A00]");
        String placaString = ler.next().toUpperCase();

        System.out.println("Digite o Pais: ");
        String paisString = ler.next().toUpperCase();

        System.out.println("Digite o Ano: ");
        int ano = ler.nextInt();

        System.out.println("Digite a Marca: ");
        String marca = ler.next().toUpperCase();

        System.out.println("Digite o combustivel em litros: ");
        Double combustivel = ler.nextDouble();

        System.out.println(
            "Digite o Tipo:"
                    + "\n1- Veiculo de Passageiros"
                    + "\n2- Veiculo de Passeio"
                    + "\n3- Veiculo Utilitario");

        int tipo = ler.nextInt();

        Placa placa = new Placa(paisString, placaString);
        Veiculo veiculo = null;

        switch (tipo) {
            case 1:
                System.out.println("Digite o numero de passageiros: ");
                int nrpassageiros = ler.nextInt();

                veiculo = new VeiculoPassageiros(placa, ano, marca, combustivel, nrpassageiros);
                break;
            case 2:
                System.out.println("Digite o consumo de km por litro: ");
                Double consumo = ler.nextDouble();

                veiculo = new VeiculoPasseio(placa, ano, marca, combustivel, consumo);
                break;
            case 3:
                System.out.println("Digite a capacidade de Carga em Toneladas: ");
                int carga = ler.nextInt();
                
                System.out.println("Digite o numero de Eixos: ");
                int nroEixos = ler.nextInt();

                veiculo = new VeiculoUtilitario(placa, ano, marca, combustivel, carga, nroEixos);
                break;
        }

        

        System.out.println(catalogo.registraVeiculo(veiculo));
        System.out.println("--------------------------------------------------------------");

    }

    private void consultaPorPlacaInterface() {

        System.out.println("Digite a Placa:  \n[formato AAA0A00]");
        String placa = ler.next().toUpperCase();
        System.out.println(catalogo.consultaPorPlaca(placa));
        System.out.println("--------------------------------------------------------------");

    }

    private void consultaPorMarcaInterface() {

        System.out.println("Digite a Marca:");
        String marca = ler.next().toUpperCase();
        System.out.println(catalogo.consultaPorMarca(marca));
        System.out.println("--------------------------------------------------------------");

    }

    private void consultaPorAnoInterface() {

        System.out.println("Digite o Ano:");
        int ano = ler.nextInt();
        System.out.println(catalogo.consultaPorAno(ano));
        System.out.println("--------------------------------------------------------------");

    }

    private void consultaPorTipoInterface() {

        System.out.println("Digite o Tipo:");
        ler.nextLine();
        String tipo = ler.nextLine().toUpperCase();
        System.out.println(catalogo.consultaPorTipo(tipo));
        System.out.println("--------------------------------------------------------------");

    }
}
