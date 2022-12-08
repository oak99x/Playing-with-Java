package com.dell;

import java.util.List;

import com.dell.recursos.DoubleConverter;
import com.dell.recursos.ListConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

//Classe inteiramente criada para receber os dados lidos do arquivo CSV
//Como usa-se uma biblioteca para ler e escrever CSV, fica neste formato em Java

public class InfoMedicamento {

    //A anotacao @CsvCustomBindByName especifica uma ligacao entre um nome de coluna da entrada CSV 
    //e um campo em um bean.

    //A anotacao @CsvCustomBindByName permite especificar uma classe que fara a traducao da origem para
    // o destino. Neta podemos implementar uma classe que pega o campo de origem do CSV e o traduz em um 
    // formato de nossa escolha - Classes presentes na pasta recursos Mapeamento utilizado para List e Double;
    // E o nome da coluna de origem também pode ser especificado.

    
    @CsvCustomBindByName(column = "SUBSTÂNCIA", converter = ListConverter.class)
    private List<String> substancia;

    @CsvBindByName(column = "CNPJ")
    private String cnpj;	

    @CsvBindByName(column = "LABORATÓRIO")
    private String laboratorio;

    @CsvBindByName(column = "CÓDIGO GGREM")
    private String codGGREM;

    @CsvBindByName(column = "REGISTRO")
    private String registro;

    @CsvBindByName(column = "EAN 1")
    private String ean1;

    @CsvBindByName(column = "EAN 2")
    private String ean2;

    @CsvBindByName(column = "EAN 3")
    private String ean3;

    @CsvBindByName(column = "PRODUTO")
    private String produto;	

    @CsvBindByName(column = "APRESENTAÇÃO")
    private String apresentacao;

    @CsvBindByName(column = "CLASSE TERAPÊUTICA")
    private String classeTerapeutica;

    @CsvBindByName(column = "TIPO DE PRODUTO (STATUS DO PRODUTO)")
    private String statusProduto_TP;

    @CsvBindByName(column = "REGIME DE PREÇO")	
    private String regimePreco;	

    @CsvCustomBindByName(column = "PF Sem Impostos", converter = DoubleConverter.class)
    private Double pf_S_I;	

    @CsvCustomBindByName(column = "PF 0%", converter = DoubleConverter.class)
    private Double pf_0;

    @CsvCustomBindByName(column = "PF 12%", converter = DoubleConverter.class)
    private Double pf_12;	

    @CsvCustomBindByName(column = "PF 17%", converter = DoubleConverter.class)
    private Double pf_17;

    @CsvCustomBindByName(column = "PF 17% ALC", converter = DoubleConverter.class)
    private Double pf_17_ALC;

    @CsvCustomBindByName(column = "PF 17,5%", converter = DoubleConverter.class)
    private Double pf_17_5;

    @CsvCustomBindByName(column = "PF 17,5% ALC", converter = DoubleConverter.class)
    private Double pf_17_5_ALC;	

    @CsvCustomBindByName(column = "PF 18%", converter = DoubleConverter.class)
    private Double pf_18;

    @CsvCustomBindByName(column = "PF 18% ALC", converter = DoubleConverter.class)
    private Double pf_18_ALC;

    @CsvCustomBindByName(column = "PF 20%", converter = DoubleConverter.class)
    private Double pf_20;

    @CsvCustomBindByName(column = "PMC 0%", converter = DoubleConverter.class)
    private Double pmc_0;	

    @CsvCustomBindByName(column = "PMC 12%", converter = DoubleConverter.class)
    private Double pmc_12;	

    @CsvCustomBindByName(column = "PMC 17%", converter = DoubleConverter.class)
    private Double pmc_17;

    @CsvCustomBindByName(column = "PMC 17% ALC", converter = DoubleConverter.class)
    private Double pmc_17_ALC;

    @CsvCustomBindByName(column = "PMC 17,5%", converter = DoubleConverter.class)
    private Double pmc_17_5;

    @CsvCustomBindByName(column = "PMC 17,5% ALC", converter = DoubleConverter.class)
    private Double pmc_17_5_ALC;

    @CsvCustomBindByName(column = "PMC 18%", converter = DoubleConverter.class)
    private Double pmc_18;	

    @CsvCustomBindByName(column = "PMC 18% ALC", converter = DoubleConverter.class)
    private Double pmc_18_ALC;

    @CsvCustomBindByName(column = "PMC 20%", converter = DoubleConverter.class)
    private Double pmc_20;

    @CsvBindByName(column = "RESTRIÇÃO HOSPITALAR")
    private String restricaoHospitalar;

    @CsvBindByName(column = "CAP")	
    private String cap;

    @CsvBindByName(column = "CONFAZ 87")
    private String confaz_87;

    @CsvBindByName(column = "ICMS 0%")
    private String icms_0;

    @CsvBindByName(column = "ANÁLISE RECURSAL")
    private String analiseRecursal;

    @CsvBindByName(column = "LISTA DE CONCESSÃO DE CRÉDITO TRIBUTÁRIO (PIS/COFINS)")
    private String consencaoCretidoTributario;

    @CsvBindByName(column = "COMERCIALIZAÇÃO 2020")	
    private String comercializacao_2020;

    @CsvBindByName(column = "TARJA")
    private String tarja;

    
    // Getters
    public InfoMedicamento(){}

    public List<String> getSubstancia() {
        return substancia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getCodGGREM() {
        return codGGREM;
    }

    public String getRegistro() {
        return registro;
    }

    public String getEan1() {
        return ean1;
    }

    public String getEan2() {
        return ean2;
    }

    public String getEan3() {
        return ean3;
    }

    public String getProduto() {
        return produto;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public String getClasseTerapeutica() {
        return classeTerapeutica;
    }

    public String getStatusProduto_TP() {
        return statusProduto_TP;
    }

    public String getRegimePreco() {
        return regimePreco;
    }

    public Double getPf_S_I() {
        return pf_S_I;
    }

    public Double getPf_0() {
        return pf_0;
    }

    public Double getPf_12() {
        return pf_12;
    }

    public Double getPf_17() {
        return pf_17;
    }

    public Double getPf_17_ALC() {
        return pf_17_ALC;
    }

    public Double getPf_17_5() {
        return pf_17_5;
    }

    public Double getPf_17_5_ALC() {
        return pf_17_5_ALC;
    }

    public Double getPf_18() {
        return pf_18;
    }

    public Double getPf_18_ALC() {
        return pf_18_ALC;
    }

    public Double getPf_20() {
        return pf_20;
    }

    public Double getPmc_0() {
        return pmc_0;
    }

    public Double getPmc_12() {
        return pmc_12;
    }

    public Double getPmc_17() {
        return pmc_17;
    }

    public Double getPmc_17_ALC() {
        return pmc_17_ALC;
    }

    public Double getPmc_17_5() {
        return pmc_17_5;
    }

    public Double getPmc_17_5_ALC() {
        return pmc_17_5_ALC;
    }

    public Double getPmc_18() {
        return pmc_18;
    }

    public Double getPmc_18_ALC() {
        return pmc_18_ALC;
    }

    public Double getPmc_20() {
        return pmc_20;
    }

    public String getRestricaoHospitalar() {
        return restricaoHospitalar;
    }

    public String getCap() {
        return cap;
    }

    public String getConfaz_87() {
        return confaz_87;
    }

    public String getIcms_0() {
        return icms_0;
    }

    public String getAnaliseRecursal() {
        return analiseRecursal;
    }

    public String getConsencaoCretidoTributario() {
        return consencaoCretidoTributario;
    }

    public String getComercializacao_2020() {
        return comercializacao_2020;
    }

    public String getTarja() {
        return tarja;
    }

 
}
