package com.HulkStore.ApiRest.Entity;

public class CurrentStatus {
	Product product;
	History history;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public History getProductHistory() {
		return history;
	}
	public void setProductHistory(History history) {
		this.history = history;
	}

}
