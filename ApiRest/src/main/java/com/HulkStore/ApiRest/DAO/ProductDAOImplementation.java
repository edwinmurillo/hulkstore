package com.HulkStore.ApiRest.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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


	public ProductDAOImplementation(NamedParameterJdbcTemplate template, JdbcTemplate updateTemplate) {  
        this.template = template;  
        this.updateTemplate = updateTemplate;
	}  
	NamedParameterJdbcTemplate template; 
	JdbcTemplate updateTemplate;
	
	@Override
	public List<CurrentStatus> findAllProducts() {
		return template.query(  "SELECT pro.productId, productName, productLabel, productType, historyPrice, subquerySum.historyQuantity\n" + 
								"FROM (SELECT SUM(historyQuantity) as historyQuantity, productId\n" + 
								"	 FROM history\n" + 
								"	 GROUP BY productId\n" + 
								"	) subquerySum\n" + 
								"JOIN history ON subquerySum.productId = history.productId\n" + 
								"JOIN product AS pro ON history.productId = pro.productId\n" + 
								"WHERE pro.productArchive = false AND\n" + 
								"	historyPrice = (SELECT historyPrice FROM history\n" + 
								"					WHERE history.historyActivityDate = (SELECT MAX(history.historyActivityDate) FROM history\n" + 
								"														 WHERE pro.productId = history.productId)\n" + 
								"				  	)", new ProductRowMapper());
	}
	@Override
	public void insertProduct(Product product, History history) {
		final String sqlProduct = "insert into product(productId, productName, productType, productLabel, productArchive) " + 
								  "values(:productId, :productName, :productType, :productLabel, :productArchive)";
		
		final String sqlHistory = "insert into history(productId, historyQuantity, historyActivityDate, historyPrice) " +
								  "values(:productId, :historyQuantity, :historyActivityDate, :historyPrice)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource productParameter = new MapSqlParameterSource()
        		.addValue("productId", product.getProductId())
				.addValue("productName", product.getProductName())
				.addValue("productType", product.getProductType())
				.addValue("productLabel", product.getProductLabel())
				.addValue("productArchive", false);
        template.update(sqlProduct, productParameter, holder);
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        SqlParameterSource historyParameter = new MapSqlParameterSource()
				.addValue("productId", product.getProductId())
				.addValue("historyQuantity", history.getProductQuantity())
				.addValue("historyActivityDate", timestamp)
				.addValue("historyPrice", history.getProductPrice());
        template.update(sqlHistory, historyParameter, holder);
		
	}
	@Override
	public void updateProduct(History history) {
		final String sqlHistory = "insert into history(productId, historyQuantity, historyActivityDate, historyPrice)" + 
								  "values(:productId, :historyQuantity, :historyActivityDate, :historyPrice)";
		 
        KeyHolder holder = new GeneratedKeyHolder();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        SqlParameterSource historyParameter = new MapSqlParameterSource()
				.addValue("productId", history.getProductId())
				.addValue("historyQuantity", history.getProductQuantity())
				.addValue("historyActivityDate", timestamp)
				.addValue("historyPrice", history.getProductPrice());
        template.update(sqlHistory, historyParameter, holder);
		
	}
	@Override
	public void deleteProduct(Product product) {
		
		final String sql = "UPDATE product SET productArchive = ? WHERE productId = ?";
		updateTemplate.update(sql, true, product.getProductId());
		
	}
	
}