package com.ntconsult.analise.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.WatchEvent;

import com.ntconsult.analise.entity.CustomerDTO;
import com.ntconsult.analise.entity.ItemDTO;
import com.ntconsult.analise.entity.SaleDTO;
import com.ntconsult.analise.entity.SellerDTO;

public class Parser {
	
	private final String HOMEPATH = System.getProperty("user.home") + "//data//in//";
	private final String OUTPATH = System.getProperty("user.home") + "//data//out//";
	
	private double mostExpensiveSaleValue = 0;
	private String mostExpensiveSaleId = "";

	private double worstSellerValue = 0;
	private String worstSellerName = "";
	
	private int eventCounter = 0;
	
	public void parse() {
		
	}
	
	public void extractLine(WatchEvent<?> event)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		int customersQuantity = 0; //output needed
		int sellersQuantity = 0; //output needed
		//admits big files
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(HOMEPATH+event.context()), "UTF-8"));
		String currentLine = reader.readLine();
		while ((currentLine = reader.readLine()) != null) {
		    String lineType = currentLine.substring(0, 3);
		    //TODO: implement factory
		    //TODO: UTF-8 problem
		    String[] result = currentLine.split("�");
		    switch(Integer.parseInt(lineType)) {
		    case 1 : 
		    	SellerDTO seller = new SellerDTO(result);
		    	sellersQuantity++;
		    	break;
		    case 2 :
		    	CustomerDTO customer = new CustomerDTO(result);
		    	customersQuantity++;
		    	break;
		    case 3 :
		    	double totalSaleValue = 0;
		    	SaleDTO sale = new SaleDTO();
		    	sale.setSaleId(result[1]);
		    	String itemsLine = result[2].replace("[","");
		    	itemsLine = itemsLine.replace("]","");
		    	String[] items = itemsLine.split(",");
		    	for (String string : items) {
					String[] item = string.split("-");
					ItemDTO myItem = new ItemDTO();
					myItem.setId(item[0]);
					myItem.setQuantity(Integer.parseInt(item[1]));
					myItem.setPrice(Double.parseDouble(item[2]));
					totalSaleValue += myItem.getPrice() * myItem.getQuantity();
				}
				mostExpensiveSaleValue = sale.evaluateMostExpensiveSaleValue(totalSaleValue, totalSaleValue);//totalSaleValue;
				mostExpensiveSaleId = sale.evaluateMostExpensiveSaleId(totalSaleValue, totalSaleValue, mostExpensiveSaleId);//sale.getSaleId();
		    	sale.setSalesmanName(result[3]);
		    	if(totalSaleValue < worstSellerValue || worstSellerValue == 0) { //output needed
		    		worstSellerValue = totalSaleValue;
		    		worstSellerName = sale.getSalesmanName();
		    	}
		    	break;
		    }
		}
		reader.close();

		Output output = new Output();
		output.writeOutFile(customersQuantity, sellersQuantity, OUTPATH+eventCounter, worstSellerName, mostExpensiveSaleId);
				
		eventCounter++;
		customersQuantity = 0;
		sellersQuantity = 0;
	}

}
