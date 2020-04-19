package com.HulkStore.ApiRest.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.HulkStore.ApiRest.Entity.Product;
import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.CurrentStatus;

public class ProductRowMapper implements RowMapper<CurrentStatus> {
	@Override
	public CurrentStatus mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CurrentStatus currentStatus = new CurrentStatus();
		Product product = new Product();
		History history = new History();
		
		product.setProductId(resultSet.getInt("productId"));
		product.setProductName(resultSet.getString("productName"));
		product.setProductType(resultSet.getString("productType"));
		product.setProductLabel(resultSet.getString("productLabel"));
		
		history.setProductId(resultSet.getInt("productId"));
		history.setProductPrice(resultSet.getInt("historyPrice"));
		history.setProductQuantity(resultSet.getInt("historyQuantity"));
		
		currentStatus.setProduct(product);
		currentStatus.setProductHistory(history);
 
        return currentStatus;
	}

}