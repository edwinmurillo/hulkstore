package com.HulkStore.ApiRest.Service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.HulkStore.ApiRest.DAO.ProductDAO;
import com.HulkStore.ApiRest.Entity.CurrentStatus;
import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.Product;

@Component
public class ProductServiceImplementation implements ProductService {

	@Resource 
	ProductDAO productDAO;
	
	@Override
	public List<CurrentStatus> findAllProducts() {
		return productDAO.findAllProducts();
	}

	@Override
	public void insertProduct(CurrentStatus currentStatus) {
		productDAO.insertProduct(currentStatus.getProduct(), currentStatus.getProductHistory());
	}

	@Override
	public void updateProduct(History history) {
		productDAO.updateProduct(history);
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.deleteProduct(product);
	}

}
