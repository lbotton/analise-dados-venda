package com.ntconsult.analise.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.WatchEvent;

import com.ntconsult.analise.entity.CustomerDTO;
import com.ntconsult.analise.entity.SaleDTO;
import com.ntconsult.analise.entity.SellerDTO;

public class Parser {
	
	private final String HOMEPATH = System.getProperty("user.home") + "//data//in//";
	
	public void parse() {
		
	}
	
	public void extractLine(WatchEvent<?> event)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(HOMEPATH+event.context()), "UTF-8"));
		String currentLine = reader.readLine();
		while ((currentLine = reader.readLine()) != null) {
		    String lineType = currentLine.substring(0, 3);
		    //TODO: implement factory
		    String[] result = currentLine.split("ç");
		    switch(lineType) {
		    case "001" : 
		    	SellerDTO seller = new SellerDTO();
		    	seller.setCpf(result[0]);
		    	seller.setName(result[1]);
		    	seller.setSalary(result[2]);
		    case "002" ://cliente
		    	CustomerDTO customer = new CustomerDTO();
		    	customer.setCnpj(result[0]);
		    	customer.setName(result[1]);
		    	customer.setBusinessArea(result[2]);
		    case "003" ://venda
		    	SaleDTO sale = new SaleDTO();
		    	sale.setSaleId(result[0]);
		    	sale.setItems(result[1]);
		    	sale.setSalesmanName(result[2]);
		    }
		}
		reader.close();
	}

}
