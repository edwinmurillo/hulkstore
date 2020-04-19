package com.HulkStore.ApiRest.DAO;

import java.util.List;

import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.Product;
import com.HulkStore.ApiRest.Entity.CurrentStatus;


public interface ProductDAO {
	
	List<CurrentStatus> findAllProducts();

	void insertProduct(Product product, History history);

	void updateProduct(Product product, History history);

	public void deleteProduct(Product product, History history);

}
