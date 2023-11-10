package com.himangshu.springboot.service;

import com.himangshu.springboot.model.Product;
import com.himangshu.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if(optional.isPresent()){
            product = optional.get();
        } else {
            throw new RuntimeException("User not found for id : = "+id);
        }
        return product;
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<Product> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        if(keyword != null){
            return productRepository.findAll(keyword,pageable);
        }
        else{
            return  productRepository.findAll(pageable);
        }
    }
}
