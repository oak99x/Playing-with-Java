package com.dell;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueriesListsTest 
{
    private static Queries consulta;

    @BeforeAll
    public static void initialize() throws IOException, CsvException{

        consulta = new Queries(new Reading(), "TA_PRECO_MEDICAMENTO.csv");

    }

    @ParameterizedTest
    @CsvSource({
        "talco",
        "Talco",
        "trol",
        "EZETROL",
        "VEROTINA",
    })
    public void Consulta1Test_ListNotIsEmpty(String medicamento)
    {
        Assertions.assertFalse(consulta.consulta1(medicamento).isEmpty());  
    }

    @ParameterizedTest
    @CsvSource({
        "nao tem",
        "errado",
        "sfsgsggdh",
    })
    public void Consulta1Test_ListIsEmpty(String medicamento)
    {
        Assertions.assertTrue(consulta.consulta1(medicamento).isEmpty());  
    }

    @Test
    public void Consulta1Test_ListIsNull()
    {
        Assertions.assertEquals(null, consulta.consulta1(""));
    }



    @ParameterizedTest
    @CsvSource({
        "7896210500354", //EAN 1
        "7897337709019", //EAN 1
        "5437456192536", //EAN 2
        "7891106914086", //EAN 3
    })
    public void Consulta2Test_ListNotIsEmpty(String codBarras)
    {
        Assertions.assertFalse(consulta.consulta2(codBarras).isEmpty());  
    }

    @ParameterizedTest
    @CsvSource({
        "9999999999999",
        "xxxxxxxxxxxxx",
    })
    public void Consulta2Test_ListIsEmpty(String codBarras)
    {
        Assertions.assertEquals(null, consulta.consulta2(codBarras));  
    }

    @ParameterizedTest
    @CsvSource({
        "44234234",
        "43242342342435345466",
        "fsdfsgh",
    })
    public void Consulta2Test_ListIsNull1(String codBarras)
    {
        Assertions.assertEquals(null, consulta.consulta2(codBarras));
    }

    @Test
    public void Consulta2Test_ListIsNull2()
    {
        Assertions.assertEquals(null, consulta.consulta2(""));
    }

    @Test
    public void Consulta3Test_ListNotIsEmpty()
    {
        Assertions.assertFalse(consulta.consulta3().isEmpty());
    }
}
