package com.perficient.techbootcamp.eCommerce.mock;

public class ProductDb {
	
	public String getProductName(int id) {
		return "Oven";
	}
	
	public int getProductID(String name) {
		return 10003;
	}
	
	public boolean isConnected() {
		return false;
	}
	
}
