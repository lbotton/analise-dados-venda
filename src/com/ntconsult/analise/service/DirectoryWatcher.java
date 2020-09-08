package com.ntconsult.analise.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.ntconsult.analise.entity.CustomerDTO;
import com.ntconsult.analise.entity.ItemDTO;
import com.ntconsult.analise.entity.SaleDTO;
import com.ntconsult.analise.entity.SellerDTO;

public class DirectoryWatcher {
	
	private final String HOMEPATH = System.getProperty("user.home") + "//data//in//";
	private final String OUTPATH = System.getProperty("user.home") + "//data//out//";
	
	private int eventCounter = 0;

	//bubble method 
	private double mostExpensiveSaleValue = 0;
	private String mostExpensiveSaleId = "";

	private double worstSellerValue = 0;
	private String worstSellerName = "";
	
	//NIO watch service
	public void watcher() throws IOException, InterruptedException, URISyntaxException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(HOMEPATH);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        
        watchEvents(watchService);
        
        watchService.close();
    }

	private void watchEvents(WatchService watchService)
			throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException {
		WatchKey key;
		while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                	Thread.sleep(1000);
                    extractLine(event);
            }
            key.reset();
        }
	}

	private void extractLine(WatchEvent<?> event)
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
		    	SellerDTO seller = new SellerDTO();
		    	seller.setCpf(result[1]);
		    	seller.setName(result[2]);
		    	seller.setSalary(result[3]);
		    	sellersQuantity++;
		    	break;
		    case 2 :
		    	CustomerDTO customer = new CustomerDTO();
		    	customer.setCnpj(result[1]);
		    	customer.setName(result[2]);
		    	customer.setBusinessArea(result[3]);
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
		    	if(totalSaleValue > mostExpensiveSaleValue) { //output needed
					mostExpensiveSaleValue = totalSaleValue;
					mostExpensiveSaleId = sale.getSaleId();
				}
		    	sale.setSalesmanName(result[3]);
		    	if(totalSaleValue < worstSellerValue || worstSellerValue == 0) { //output needed
		    		worstSellerValue = totalSaleValue;
		    		worstSellerName = sale.getSalesmanName();
		    	}
		    	break;
		    }
		}
		reader.close();

		writeOutFile(customersQuantity, sellersQuantity);
		
		customersQuantity = 0;
		sellersQuantity = 0;
	}

	private void writeOutFile(int customersQuantity, int sellersQuantity) throws IOException {
		FileWriter fileWriter = new FileWriter(OUTPATH+eventCounter+"-out.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println("Customers Quantity: " + customersQuantity);
	    printWriter.println("Sellers Quantity: " + sellersQuantity);
	    printWriter.println("Worst Seller: " + worstSellerName);
	    printWriter.println("Most Expensive Sale ID: " + mostExpensiveSaleId);
	    printWriter.close();
	    eventCounter++;
	}
	
}
