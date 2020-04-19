package com.HulkStore.ApiRest.Service;

import java.util.List;

import com.HulkStore.ApiRest.Entity.Product;
import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.CurrentStatus;

public interface ProductService {

	List<CurrentStatus> findAllProducts();

	void insertProduct(CurrentStatus currentStatus);

	void updateProduct(History history);

	public void deleteProduct(Product product);

}