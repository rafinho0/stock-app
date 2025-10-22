package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.ProductResponseDTO;
import br.com.aegis.stock.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> findAllProducts();

    ProductResponseDTO findProductById(Long id);

    ProductResponseDTO create(ProductResponseDTO productResponseDTO);

    ProductResponseDTO update(Long id, ProductResponseDTO productResponseDTO);

    void deleteById(Long id);

}
