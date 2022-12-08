package com.dell.recursos;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DoubleConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        
        if(value.isEmpty()){
            return -1.0;
        }

        String v = "";
        Double valor;

        if(value.contains(",")){
            v = value.replace(",", ".");    
        }
        
        valor = Double.parseDouble(v) ;
        
        return valor;
    }
}

