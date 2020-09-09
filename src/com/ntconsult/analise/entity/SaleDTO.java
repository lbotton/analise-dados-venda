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
	
	
	
}
