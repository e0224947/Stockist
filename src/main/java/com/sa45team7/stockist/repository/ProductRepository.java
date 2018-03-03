package com.sa45team7.stockist.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa45team7.stockist.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product>
{
	//reorder report usecase
	@Query (value="SELECT DISTINCT p.supplierId FROM Product p", nativeQuery=true)
	ArrayList<Integer> findSupplierIdsWithProducts();
	
	
	@Query("SELECT p FROM Product p where p.partNumber = :id")
	Product findProductByPartNumber(@Param("id") Integer id);
	
	
	@Query("SELECT p FROM Product p where p.partName = :name")
	ArrayList<Product> findProductByName(@Param("name") String name);
	
	
	@Query("SELECT p FROM Product p where p.supplier.supplierId = :supplierId")
	ArrayList<Product> findProductBySupplierId(@Param("supplierId") Integer supplierId);
	
	
	@Query("SELECT p FROM Product p where p.brand = :brand")
	ArrayList<Product> findProductByBrand(@Param("brand") String brand);
	
	
	@Query(value = "SELECT * FROM Product WHERE partNumber like %:partNumber%", nativeQuery = true)
	ArrayList<Product> findByPartNumberContaining(@Param("partNumber") String partNumber);
	
	
	ArrayList<Product> findByBrandContaining(String brand);
	
	
	ArrayList<Product> findByPartNameContaining(String partName);
	
	
	ArrayList<Product> findByShelfLocationContaining(String shelfLocation);
	
	@Query("SELECT DISTINCT p.partNumber FROM Product p")
	ArrayList<Integer> findPartNumber();
	
}
