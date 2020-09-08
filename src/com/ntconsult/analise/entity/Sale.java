package com.ntconsult.analise.entity;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Sale {
	
	private String saleId;
	
	private ArrayList<Item> items;
	
	private String salesmanName;

}
