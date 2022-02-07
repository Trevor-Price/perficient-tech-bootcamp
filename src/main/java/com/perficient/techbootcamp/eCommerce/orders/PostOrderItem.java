package com.perficient.techbootcamp.eCommerce.orders;

public class PostOrderItem {
	private Long productId;
	private int quantity;
	
	public PostOrderItem() {
		super();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
