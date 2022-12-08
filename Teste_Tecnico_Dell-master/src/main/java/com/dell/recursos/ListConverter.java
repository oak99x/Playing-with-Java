package com.dell.recursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;


public class ListConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        
        String linha[] = value.split(";") ;
        List<String> list = new ArrayList<String>(Arrays.asList(linha));
        
        return list;
    }
}
