package com.HulkStore.ApiRest.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.HulkStore.ApiRest.Entity.Product;
import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.CurrentStatus;
import com.HulkStore.ApiRest.Mapper.ProductRowMapper;
@Repository
public class ProductDAOImplementation implements ProductDAO {


	public ProductDAOImplementation(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}  
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	NamedParameterJdbcTemplate template;  
	

	@Override
	public List<CurrentStatus> findAllProducts() {
		return template.query("select * from product JOIN", new ProductRowMapper());
	}
	@Override
	public void insertProduct(Product product, History history) {
		final String sqlProduct = "insert into product(productId, productName, productType, productLabel) values(:productId, :productName, :productType, :productLabel)";
		final String sqlHistory = "insert into history(productId, historyQuantity, historyActivityDate, historyPrice) values(:productId, :historyQuantity, :historyActivityDate, :historyPrice)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource productParameter = new MapSqlParameterSource()
        		.addValue("productId", product.getProductId())
				.addValue("productName", product.getProductName())
				.addValue("productType", product.getProductType())
				.addValue("productLabel", product.getProductLabel());
        template.update(sqlProduct,productParameter, holder);
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        SqlParameterSource historyParameter = new MapSqlParameterSource()
				.addValue("productId", product.getProductId())
				.addValue("historyQuantity", history.getProductQuantity())
				.addValue("historyActivityDate", simpleDateFormat.format(timestamp))
				.addValue("historyPrice", history.getProductPrice());
        template.update(sqlHistory,historyParameter, holder);
		
	}
	@Override
	public void updateProduct(Product product, History history) {
		final String sqlHistory = "insert into history(productId, historyQuantity, historyActivityDate, historyPrice) values(:productId, :historyQuantity, :historyActivityDate, :historyPrice)";
		 
        KeyHolder holder = new GeneratedKeyHolder();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        SqlParameterSource historyParameter = new MapSqlParameterSource()
				.addValue("productId", product.getProductId())
				.addValue("historyQuantity", history.getProductQuantity())
				.addValue("historyActivityDate", simpleDateFormat.format(timestamp))
				.addValue("historyPrice", history.getProductPrice());
        template.update(sqlHistory,historyParameter, holder);
		
	}
	@Override
	public void deleteProduct(Product product, History history) {
		
		
		final String sql = "update product set productArchived =: productArchived where productId =: productId";
		 
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
				.addValue("productId", product.getProductId())
				.addValue("productArchived", false);
        template.update(sql,param, holder);
		
	}
	
}