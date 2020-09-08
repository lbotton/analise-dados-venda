package com.ntconsult.analise.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDTO {
	
	private String cnpj;
	
	private String name;
	
	private String businessArea;
	
}
