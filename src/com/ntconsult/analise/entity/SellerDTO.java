package com.ntconsult.analise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SellerDTO {

	private String cpf;
	
	private String name;
	
	private String salary;
	
	private double amount;

	public SellerDTO(String[] result) {
		super();
		this.cpf = result[1];
		this.name = result[2];
		this.salary = result[3];
	}
	
	
	
}
