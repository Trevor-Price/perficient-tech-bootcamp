package com.perficient.techbootcamp.eCommerce.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductDbServiceTests {
	
	@Mock
	ProductDb dbMock;
	
	@Test
	public void testIsConnected() {
		assertNotNull(dbMock);
		ProductDbService productDbService = new ProductDbService(dbMock);
		when(dbMock.isConnected()).thenReturn(true);
		boolean check = productDbService.isConnected();
		assertTrue(check);
	}
	
	@Test
	public void testGetProductNameQuery() {
		assertNotNull(dbMock);
		ProductDbService productDbService = new ProductDbService(dbMock);
		when(dbMock.getProductName(Mockito.anyInt())).thenReturn(null);
		when(dbMock.getProductName(10003)).thenReturn("Oven");
		String check1 = productDbService.queryProductName(10003);
		String check2 = productDbService.queryProductName(48328);
		assertEquals("Oven" ,check1);
		assertEquals(null ,check2);
	}
	
	@Test
	public void testGetProductQuery() {
		assertNotNull(dbMock);
		ProductDbService productDbService = new ProductDbService(dbMock);
		when(dbMock.getProductID(Mockito.anyString())).thenReturn(-1);
		when(dbMock.getProductID("Oven")).thenReturn(10003);
		int check1 = productDbService.queryProductID("Oven");
		int check2 = productDbService.queryProductID("Frying Pan");
		assertEquals(10003 ,check1);
		assertEquals(-1 ,check2);
	}
	
}
