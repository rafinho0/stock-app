package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.ProductResponseDTO;
import br.com.aegis.stock.model.Category;
import br.com.aegis.stock.model.Product;
import br.com.aegis.stock.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {

        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Override
    // @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    // @Transactional(readOnly)
    public ProductResponseDTO findProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find product id " + id));

        return new ProductResponseDTO(product);
    }

    @Override
    public Product findProductEntityById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product"));
    }

    /*
        Will add the implementation later
    */
    @Override
    @Transactional
    public ProductResponseDTO create(ProductResponseDTO productResponseDTO) {

        Category category = categoryService.findCategoryById(productResponseDTO.getCategoryId());

        Product product = new Product();

        BeanUtils.copyProperties(productResponseDTO, product);

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, ProductResponseDTO productResponseDTO) {

        Product existingProduct = findProductEntityById(id);

        Category newCategory = categoryService.findCategoryById(productResponseDTO.getCategoryId());

        existingProduct.setName(productResponseDTO.getName());
        existingProduct.setLongDescription(productResponseDTO.getLongDescription());
        existingProduct.setPrice(productResponseDTO.getPrice());

        existingProduct.setCategory(newCategory);

        Product updateProduct = productRepository.save(existingProduct);

        return new ProductResponseDTO(updateProduct);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Can't find product");
        }

        productRepository.deleteById(id);
    }
}
