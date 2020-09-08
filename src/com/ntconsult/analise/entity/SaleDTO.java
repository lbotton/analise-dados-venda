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
	
	private ArrayList<ItemDTO> items;
	
	private String salesmanName;
	
}
