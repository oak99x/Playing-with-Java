package com.dell;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.opencsv.exceptions.CsvException;
import org.apache.commons.math3.util.Precision;

public class Queries {

    private Reading reading;

    private List<InfoMedicamento> listaGeral;           // lista completa dos medicamentos
    private List<InfoMedicamento> medicamentosPeloNome; // lista de medicamentos pelo nome
    private List<InfoMedicamento> medicamentosPeloCB;   // lista de medicamentos pelo código de barras
    private List<Double> listPMCS;                      // lista com os valores do PMC 0% maximo, menor e diferença entre eles
    private List<Double> listaCCT;                      // lista com os valores das porcentagens (Negativa, Neutra e Positiva)
                                                        // com base LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS) de 2020

    public Queries(Reading reading, String file) throws IOException, CsvException {
        this.reading = reading;
        this.reading.readingCSV(file);
        this.listaGeral = reading.getListaInfoMedicamentos();
    }

    // Vale ressaltar que a maioria das consultas usa-se stream
    // Este recurso nos facilita o desenvolvimento, reduz o tamanho do codigo e simplifica o uso do paralelismo
    // Entao visualmente estao bem reduzidas

    /**
     * 1. Consultar medicamentos pelo nome] Permitir que o usuário informe o nome domedicamento
     * (ou parte do nome do medicamento) que desejar e como resultado o programa deverá exibir:
     * a. Uma lista com os medicamentos encontrados e suas informações (Nome,Produto,Apresentação
     *    e valor PF Sem Impostos);
     * Atenção: somente devem aparecer no resultado os registros de produtos que foram comercializados
     * em 2020 (observar a coluna de dados “COMERCIALIZAÇÃO 2020”).
     * 
     * @param
     */
    public List<InfoMedicamento> consulta1(String medicamento) {

        // considera-se aqui  o nome comercial do medicamento
        // Justificativa: o mais comum o é que o usuario pesquise por dipirona ao inves de Metamizol
        if(medicamento.equals("")){
            return null;
        }

        String med = medicamento.toUpperCase();
        medicamentosPeloNome = listaGeral.stream()
                .filter(m -> m.getProduto().contains(med)) //Filtra os objetos que o getProduto() contem a string
                .filter(m -> m.getComercializacao_2020().equals("Sim"))//Filtra os objetos com Comercializacao em 2020
                .toList(); // retorna uma lista com os objetos que passaram no filtro
        
        // Caso queira considerar a substancia como nome do medicamento
        // segue o algoritmo comentado abaixo
        //
        //medicamentosPeloNome = new ArrayList<InfoMedicamento>();
        //substancias como nome
        // for(InfoMedicamento m : listaGeral){
        //     List<String> temp = m.getSubstancia();
        //     for(String s : temp){
        //         if(s.contains(med)){
        //             if(m.getComercializacao_2020().equals("Sim")){
        //                 medicamentosPeloNome.add(m);
        //             }
        //         }
        //     }
        // }

        return medicamentosPeloNome;
    }

    /**
     * 2. O programa deverá solicitar ao usuário o número correspondente ao código de barras
     * de um produto (coluna de dados “EAN 1”, por exemplo ‘525516020019503’) e então:
     * a. Localizar todos os registros referentes a este produto, independentemente de terem sido
     *    comercializados ou não em 2020;
     * b. Dentre todos os registos encontrados, identificar o Preço Máximo ao Consumidor (alíquota
     *    de 0%, coluna de dados “PMC 0%”) mais alto e o mais baixo. Exibir na tela o mais alto, o mais
     *    baixo e a diferença entre eles.
     * 
     * @param
     */
    public List<Double> consulta2(String codBarras) {

        //Verificacoes iniciais
        //Se string for vazia retorna null direto
        if(codBarras.equals("")){
            return null;
        }

        //Se o tamanha da string for diferente de 13 characters retorna null direto
        if(codBarras.length() != 13){
            return null;
        }

        //Passado verificacaoes, comeca-se a filtragem
        medicamentosPeloCB = listaGeral.stream()
                .filter(m -> m.getEan1().equals(codBarras) //Filtra os objetos em que contenham o codigo de barras
                          || m.getEan2().equals(codBarras) //Passa pelas 3 colunas EAN 1, EAN 2, EAN 3
                          || m.getEan3().equals(codBarras))
                .toList();// retorna uma lista com os objetos que passaram no filtro

        //Se depois da filtragem, a lista ainda retornar vazia, entao retorna-se null
        if(medicamentosPeloCB.isEmpty()){
            return null;
        }

        //Depois do filtro, passa-se para a procura do PMC 0% Maximo e PMC 0% Minimo
        // criase 3 variaveis para receber os valores
        Double pmcMinimo = 0.0;
        Double pmcMaximo = 0.0;
        Double pmcDiferenca = 0.0;

        //E para pegar o valor maximo e minimo, realizarei uma ordenacao crescente da lista
        // Ou seja, meu menor valor vai ser o primeiro e o maior o ultimo

        //Se a lista filtrada tem mais de uma elemento da-se um sorte nela usando um Comparator
        if (medicamentosPeloCB.size() > 1) {

            // Comparator - compara os objetos pelo valor do PMC 0%
            Comparator<InfoMedicamento> pmcComparator = (o1, o2) -> o1.getPmc_0().compareTo(o2.getPmc_0());
            medicamentosPeloCB.sort(pmcComparator);

            //Depois da ordenacao guarda os valores Minimo e Maximo
            pmcMinimo = medicamentosPeloCB.get(0).getPmc_0();
            pmcMaximo = medicamentosPeloCB.get(medicamentosPeloCB.size() - 1).getPmc_0();

        } else {
            pmcMinimo = medicamentosPeloCB.get(0).getPmc_0();
            pmcMaximo = medicamentosPeloCB.get(medicamentosPeloCB.size() - 1).getPmc_0();;
        }

        //Calcula diferenca e guarda na variavel Diferenca
        pmcDiferenca = pmcMaximo-pmcMinimo;

        //Adiciona-se esses dados em uma lista e retorna-se
        listPMCS = Arrays.asList(pmcMinimo, pmcMaximo, pmcDiferenca);

        return listPMCS;
    }

    //Como a consulta 3 as porcentagens
    //Cria-se um get para a lista de medicamentos encontrados pelo codigo de barra
    public List<InfoMedicamento> getMedicamentosPeloCB(){
        return medicamentosPeloCB;
    }


    /**
     * 3. Com base somente nos produtos que foram comercializados em 2020, o programa deverá:
     * a. Consultar a coluna de dados “LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)”
     *    para determinar o percentual de produtos classificados como “Negativa”, “Neutra” ou 
     *    “Positiva” para esta coluna.
     * b. Mostrar os respectivos valores percentuais da seguinte maneira (dados fictícios):
     * @param
    */
    public List<Double> consulta3() {

        //inicia-se as variaveis
        int negativa = 0;
        int neutra   = 0;
        int positiva = 0;
        double vTotal2020 = 0;

        //busca sequenical passando por toda a planilha/listageral
        for(InfoMedicamento m : listaGeral){
            //Para cada elemento da lista verifica se a coluna Comercializacao em 2020 é igual a "Sim"
            //Se for entra no escopo do if, incrementa o contados de elementos de 2020 e logo abaixo segue um switch
           if(m.getComercializacao_2020().equals("Sim")){
                vTotal2020++;

                //Verifica qual é classificacao do elemento e incrementa a variavel de seu respectivo case
                switch(m.getConsencaoCretidoTributario()){
                    case "Negativa":
                        negativa++;
                        break;
                    case "Neutra":
                        neutra++;
                        break;
                    case "Positiva":
                        positiva++;
                        break;
                    default:
                        break;
                    
                }
            }
        }

        //Aqui Calcula-se as porcentagens de cada classificacao
        //Porcentagem = (valor obtido x 100) / Valor total
        //Precision.round vem da biblioteca de componentes matemáticos Commons Math
        //E serve para arrendondar com maior precisão e é util para comparar numeros.
        Double percentualNegativa = Precision.round(((negativa * 100) / vTotal2020), 2);
        Double percentualNeutra   = Precision.round(((neutra   * 100) / vTotal2020), 2);
        Double percentualPositiva = Precision.round(((positiva * 100) / vTotal2020), 2);

        //Mosta uma lista com os valores percentuais encontrados e retorna
        listaCCT = Arrays.asList(percentualNegativa, percentualNeutra, percentualPositiva);

        return listaCCT;
    }

    public String grafico(Double percentual){

        String grafico = "";

        //Se a porcentagem for menor que 10 adiciona mais um espaco no inicio
        //isso serve para alinhar os dados na hora de imprimir no console
        if(percentual<10){
            grafico = grafico.concat(" ");
        }

        //para cada porcentagem, se calcula a linha de asteriscos
        //fazendo uma concatenacao de asteriscos ate chegar em seu valor 
        for(int i=0; i < percentual; i++){
            grafico = grafico.concat("*");
        }
        
        return grafico;
    }
}
