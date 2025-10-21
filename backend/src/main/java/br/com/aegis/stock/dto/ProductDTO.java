package br.com.aegis.stock.dto;

import br.com.aegis.stock.model.Category;
import br.com.aegis.stock.model.Product;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String name;
    private String imgURL;
    private BigDecimal price;
    private String longDescription;
    private Category category;


    public ProductDTO() {}

    public ProductDTO(Product product) {
        BeanUtils.copyProperties(product, this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
