package com.HulkStore.ApiRest.Entity;

public class History {
	int id;
	int quantity;
	int price;
	
	public int getProductId() {
		return id;
	}
	public void setProductId(int id) {
		this.id = id;
	}
	
	public int getProductQuantity() {
		return quantity;
	}
	public void setProductQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getProductPrice() {
		return price;
	}
	public void setProductPrice(int price) {
		this.price = price;
	}

}
