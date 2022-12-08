package com.dell;

import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueriesValuesTest 
{
    private static Queries consulta;

    @BeforeAll
    public static void initialize() throws IOException, CsvException{

        consulta = new Queries(new Reading(), "TA_PRECO_MEDICAMENTO.csv");

    }

    @ParameterizedTest
    @CsvSource({
        "7.58, 7.58, 0.0, 7896210500354",       //EAN 1
        "161.0, 161.0, 0.0, 7897337709019",     //EAN 1
        "1424.48, 1424.48, 0.0, 5437456192536", //EAN 2
        "8.78, 8.78, 0.0, 7891106914086",       //EAN 3
    })
    public void Consulta2Test_ValoresRetornados(Double min,  Double max, Double dif, String codBarras)
    {
        List<Double> valores = consulta.consulta2(codBarras);

        Double pmcMinimo = valores.get(0);
        Double pmcMaximo = valores.get(1);
        Double pmcDiferenca = valores.get(2);

        Assertions.assertEquals(min, pmcMinimo);
        Assertions.assertEquals(max, pmcMaximo); 
        Assertions.assertEquals(dif, pmcDiferenca);  
    }

    @ParameterizedTest
    @CsvSource({
        "33.43, 0.36, 66.21",
    })

    public void Consulta3Test_ValoresRetornados(Double negativaP,  Double neutraP, Double positivaP)
    {
        List<Double> valores = consulta.consulta3();

        Double pNeg = valores.get(0);
        Double pNeu = valores.get(1);
        Double pPos = valores.get(2);

        Assertions.assertEquals(negativaP, pNeg);
        Assertions.assertEquals(neutraP, pNeu); 
        Assertions.assertEquals(positivaP, pPos);

        Assertions.assertEquals(100, (pNeg+pNeu+pPos));
    }
}
