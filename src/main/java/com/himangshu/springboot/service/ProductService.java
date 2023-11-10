package com.himangshu.springboot.service;

import com.himangshu.springboot.model.Product;
import org.springframework.data.domain.Page;


public interface ProductService {

    Product saveProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);


    Page<Product> listAll(int pageNumber, String sortField, String sortDir, String keyword);

}
