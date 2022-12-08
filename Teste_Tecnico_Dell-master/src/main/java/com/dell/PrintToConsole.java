package com.dell;

import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvException;

//Classe totalmente para a formatação dos dados filtrados
public class PrintToConsole {

    Queries consulta;

    public PrintToConsole() throws IOException, CsvException {

        consulta = new Queries(new Reading(), "TA_PRECO_MEDICAMENTO.csv");

    }


    //Para as Consulta 1 e Consulta 1, se as listas listas retorno da classe Queries forem null
    //Printa-se uma mensagem dizendo que medicamento nao encontrado.
    //Se estiverem ok, sem problemas, segue com a formatacao e logo printa para o usuario
    public void printConsulta1(String medicamento){

        //inicia-se a lista de medicamentos encontrados na consulta 1 - consulta pelo nome
        List<InfoMedicamento> list =  consulta.consulta1(medicamento);

        //se a lista for null ou vazia printa dizendo que não encontrou
        if(list == null || list.isEmpty()){
            System.out.println("Medicamento nao encontrado");
        }else{

            //se estiver tudo ok printa os medicamentos encontrados no console
            for (InfoMedicamento m : list) {  
                System.out.println( "Produto       : " + m.getProduto()
                                +"\nApresentacao  : " + m.getApresentacao()
                                +"\nValor PF(S.I) : " + m.getPf_S_I()
                                +"\nSubstancias   : " + m.getSubstancia()
                                +"\n---------------------------------------------------------------------\n"
                            );
            }
        }  
        
    }

    public void printConsulta2(String codBarras){

        //inicia-se a lista de valores encontrados na consulta 2 - index 1 Minimo, 2 Maximo e 3 Diferenca
        List<Double> listValores =  consulta.consulta2(codBarras);

        //se a lista for null ou vazia printa dizendo que não encontrou
        if(listValores == null){
            System.out.println("Codigo de Barras nao encontrado");
        }else{

            //puxa-se a lista de medicamentos encontrados pelo codigo de barra na consulta2
            //se estiver tudo ok printa os medicamentos encontrados no console
            List<InfoMedicamento> listMed =  consulta.getMedicamentosPeloCB();
            for (InfoMedicamento m : listMed) {  
                System.out.println( "Produto       : " + m.getProduto()
                                +"\nApresentacao  : " + m.getApresentacao()
                                +"\nValor PMC 0%  : " + m.getPmc_0()
                                +"\nSubstancias   : " + m.getSubstancia()
                                +"\n---------------------------------------------------------------------\n"
                            );
            }

            //depois de mostrar os medicamentos encontrados, printa os valores
            //PMC 0% Minimo, PMC 0% Maximo e Diferenca entre eles
            System.out.println("Precos Maximo e Maximo ao Consumidor");
            System.out.println("PMC 0% Minimo: " + listValores.get(0));
            System.out.println("PMC 0% Maximo: " + listValores.get(1));
            System.out.println("Diferenca    : " + listValores.get(2));
            System.out.println();
        }
    }

    public void printConsulta3(){

        //inicia-se a lista de porcentagens encontrados na consulta 3 - index 1 Negativa, 2 Neutra e 3 Positiva
        List<Double> list =  consulta.consulta3();

        //Formatacao para printar no console em formato de tabela
        System.out.printf("%s", "PERCENTUAL DA LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)");
        System.out.printf("\n%-10s %20s %20s", "CLASSIFICACAO", "PERCENTUAL", "GRAFICO");
        System.out.format("\n%-25s %.2f%-16s %s",  "Negativa" , list.get(0), "%",  consulta.grafico(list.get(0)));
        System.out.format("\n%-25s %.2f%-16s %s",  "Neutra"   , list.get(1), "%",  consulta.grafico(list.get(1)));
        System.out.format("\n%-25s %.2f%-16s %s",  "Positiva" , list.get(2), "%",  consulta.grafico(list.get(2)));
        System.out.format("\n%-25s %.2f%s \n"   ,  "Total"    , (list.get(0)+list.get(1)+list.get(2)),"%");
        System.out.println();
    }
    
}
