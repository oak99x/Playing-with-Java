package com.dell;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Reading {

    private List<InfoMedicamento> info;
 
    public Reading() {}

    public void readingCSV(String file) {

       // Para a leitura  uma biblioteca  para ler e escrever CSV, usa-se aqui o Opencsv
       // Opencsv eh uma biblioteca de analisador CSV facil de usar para Java.
       // Usei esta biblioteca, pois, alem de facilitar de nao ter de codar uma leitura completa a mao
       // O Opencsv suporta todas as coisas básicas do tipo CSV, como numeros arbitrarios de valores por linha,
       // Manipulação de entradas entre aspas com retornos de carro incorporados, Separador configuravel e caracteres de aspas, etc.
       // Sua dependencias estao localizadas no pom.xml e 
        try {

            info = new CsvToBeanBuilder(new FileReader(file))
                    .withType(InfoMedicamento.class) //Define o tipo do bean a ser preenchido
                    .withSeparator(';')   //Define separador
                    .withIgnoreLeadingWhiteSpace(true)// Ignorar os espaços em branco
                    .withIgnoreEmptyLine(true)// Ignorar Linha Vazia
                    .build() //Cria o CsvToBean a partir das informações fornecidas.
                    .parse();//Analisa a entrada com base em parametros ja definidos e cria um lista
                             //de beans preenchidos com base na entrada

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Caso tenha algum problema dentro do seu try ele vai para o catch  e para saber qual foi
        // o erro que deu usa-se metodo printStackTrace() que esta dentro das classes “Exception”
    }

    //Getter para a lista de objetos criados
    public List<InfoMedicamento> getListaInfoMedicamentos(){
        return info;
    }
    
}
