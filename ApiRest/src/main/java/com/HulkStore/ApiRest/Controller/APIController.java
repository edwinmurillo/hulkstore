package com.HulkStore.ApiRest.Controller;

import com.HulkStore.ApiRest.Entity.CurrentStatus;
import com.HulkStore.ApiRest.Entity.History;
import com.HulkStore.ApiRest.Entity.Product;

import com.HulkStore.ApiRest.Service.ProductService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hulkStore")
public class APIController {
	@Resource 
	ProductService productService;
	
	@GetMapping(value = "/productList")
	public List<CurrentStatus> getProducts() {
		return productService.findAllProducts();
	
	}
	
	@PostMapping(value = "/createProduct")
	public void createProduct(@RequestBody CurrentStatus currentStatus) {
		productService.insertProduct(currentStatus);
	
	}
	@PutMapping(value = "/updateProduct")
	public void updateProduct(@RequestBody History history) {
		productService.updateProduct(history);
	
	}
	@PutMapping(value = "/deleteProduct")
	public void deleteProduct(@RequestBody Product product) {
		productService.deleteProduct(product);
	
	}
	
}