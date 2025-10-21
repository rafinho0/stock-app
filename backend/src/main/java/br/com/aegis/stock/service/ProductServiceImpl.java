package br.com.aegis.stock.service;

import br.com.aegis.stock.model.Product;
import br.com.aegis.stock.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {

        return productRepository.findById(id)
                // If we don't have a product, we'll throw an exception
                .orElseThrow(() -> new RuntimeException("Did not find product with id " + id));
    }

    @Override
    @Transactional
    public Product save(Product product) {

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        productRepository.deleteById(id);
    }
}
