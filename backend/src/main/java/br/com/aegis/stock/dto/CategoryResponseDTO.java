package br.com.aegis.stock.dto;

import br.com.aegis.stock.model.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    // This DTO is the Response, which is the one we're returning to the user after it's creation

    private Long id;
    private String name;


    public CategoryResponseDTO(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}
