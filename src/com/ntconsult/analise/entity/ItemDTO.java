package com.ntconsult.analise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemDTO {
	
	private String id;
	
	private int quantity;
	
	private double price;

}
