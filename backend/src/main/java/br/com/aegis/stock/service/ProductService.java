package br.com.aegis.stock.service;

import br.com.aegis.stock.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product findProductById(Long id);

    Product save(Product product);

    void deleteById(Long id);

}
