package com.ntconsult.analise.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Output {
	
	public void writeOutFile(int customersQuantity, int sellersQuantity, String path,
			String worstSellerName, String mostExpensiveSaleId) throws IOException {
		FileWriter fileWriter = new FileWriter(path+"-out.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println("Customers Quantity: " + customersQuantity);
	    printWriter.println("Sellers Quantity: " + sellersQuantity);
	    printWriter.println("Worst Seller: " + worstSellerName);
	    printWriter.println("Most Expensive Sale ID: " + mostExpensiveSaleId);
	    printWriter.close();
	    
	}

}
