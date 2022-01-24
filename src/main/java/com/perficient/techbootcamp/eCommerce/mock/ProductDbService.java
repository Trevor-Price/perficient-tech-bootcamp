package com.perficient.techbootcamp.eCommerce.mock;

import java.util.List;

public class ProductDbService {
	
	ProductDb db;
	
	public ProductDbService(ProductDb db) {
		this.db = db;
	}
	
	public String queryProductName(int id) {
		return db.getProductName(id);
	}
	
	public int queryProductID(String name) {
		return db.getProductID(name);
	}
	
	public boolean isConnected() {
		return db.isConnected();
	}
}
