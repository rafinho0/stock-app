package br.com.aegis.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    // P.S: This DTO is only for the creation of the category.
    // In other words, the user just need to inform the name of the category to create it
    private String name;
}
