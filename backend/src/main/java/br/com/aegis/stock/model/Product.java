package br.com.aegis.stock.model;

import br.com.aegis.stock.dto.ProductResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public Product(ProductResponseDTO productResponseDTO) {
        BeanUtils.copyProperties(productResponseDTO, this);
    }
}
