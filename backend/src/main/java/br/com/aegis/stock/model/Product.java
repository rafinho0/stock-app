package br.com.aegis.stock.model;

import br.com.aegis.stock.dto.ProductDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "img_url", nullable = false)
    private String imgURL;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "long_description")
    private String longDescription;

    @ManyToOne(cascade =
                    {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH},
               fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // private int quantity;


    public Product() {

    }

    public Product(String name, String imageURL, BigDecimal price, String longDescription) {
        this.name = name;
        this.imgURL = imageURL;
        this.price = price;
        this.longDescription = longDescription;
    }

    public Product(ProductDTO productDTO) {
        BeanUtils.copyProperties(productDTO, this);
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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
