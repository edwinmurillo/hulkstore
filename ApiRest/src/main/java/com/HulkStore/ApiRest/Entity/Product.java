package com.HulkStore.ApiRest.Entity;

public class Product {
	int id;
	String name;
	String type;
	String label;
	String archive;
	
	public int getProductId() {
		return id;
	}
	public void setProductId(int id) {
		this.id = id;
	}
	
	public String getProductName() {
		return name;
	}
	public void setProductName(String name) {
		this.name = name;
	}
	
	public String getProductType() {
		return type;
	}
	public void setProductType(String type) {
		this.type = type;
	}
	
	public String getProductLabel() {
		return label;
	}
	public void setProductLabel(String label) {
		this.label = label;
	}
	
	public String getProductArchived() {
		return archive;
	}
	public void setProductArchived(String archive) {
		this.archive = archive;
	}

}
