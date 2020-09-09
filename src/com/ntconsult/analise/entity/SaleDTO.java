package com.ntconsult.analise.entity;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SaleDTO {
	
	private String saleId;
	
	private String items;
	
	private String salesmanName;

	public SaleDTO(String[] result) {
		super();
		this.saleId = result[1];
		
		this.items = result[2];
		this.salesmanName = result[3];
		
	}
	
	public double evaluateMostExpensiveSaleValue(double totalSaleValue, double mostExpensiveSaleValue) {
		if(totalSaleValue > mostExpensiveSaleValue) {
			return totalSaleValue;
		}
		return mostExpensiveSaleValue;
	}
	
	public String evaluateMostExpensiveSaleId(double totalSaleValue, double mostExpensiveSaleValue, String mostExpensiveSaleId) {
		if(totalSaleValue > mostExpensiveSaleValue) {
			return this.saleId;
		}
		return mostExpensiveSaleId;
	}
	
}
