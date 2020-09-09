package com.ntconsult.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ntconsult.analise.entity.SaleDTO;

public class SaleDTOTest {
	
	@Test
	public void testConcatenate() {
        SaleDTO sale = new SaleDTO();
        
        double totalSaleValue = 5;
        double mostExpensiveSaleValue = 10;
        
        double result = sale.evaluateMostExpensiveSaleValue(totalSaleValue, mostExpensiveSaleValue);

        assertEquals(10, result,0);

    }
}
