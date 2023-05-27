package com.vsu.service;

import com.vsu.entity.Product;
import com.vsu.exception.NotFoundException;
import com.vsu.repository.ProductRepo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductService {
    private static final int MIN_COUNT_UPDATE = 1;
    private final ProductRepo productRepo;
    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product insertProduct(Product product) {
        if (productRepo.insert(product) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "Product with id {0} is not inserted", product.getId());
            return null;
        } else {
            return product;
        }
    }

    public int deleteProduct(Long id) {
        Product product = productRepo.selectById(id);
        if (product == null) {
            LOGGER.log(Level.INFO, "Product with id {0} is not deleted", id);
            throw new NotFoundException("This product does not exists");
        }
        return productRepo.deleteById(id);
    }

    public Product selectById(String id) {
        Long idL = Long.parseLong(id);
        return productRepo.selectById(idL);
    }

    public List<Product> selectAllByUserId(Long id) {
        return productRepo.selectAllByUserId(id);
    }

    public void updateByID(Product product) {
        if (productRepo.updateByID(product) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "Product with id {0} is not updated", product.getId());
        }
    }
}
