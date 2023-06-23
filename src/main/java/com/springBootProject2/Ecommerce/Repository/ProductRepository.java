package com.springBootProject2.Ecommerce.Repository;

import com.springBootProject2.Ecommerce.Enum.ProductCategory;
import com.springBootProject2.Ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
