package br.com.aegis.stock.dto;

import br.com.aegis.stock.model.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String imgURL;
    private BigDecimal price;
    private String longDescription;

    // We'll use this to simplify the category DTO
    private Long categoryId;
    private String categoryName;


    public ProductResponseDTO(Product product) {
        BeanUtils.copyProperties(product, this);

        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
        }
    }
}
