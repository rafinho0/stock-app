package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.CategoryRequestDTO;
import br.com.aegis.stock.dto.CategoryResponseDTO;
import br.com.aegis.stock.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> findAllCategories();

    CategoryResponseDTO findCategoryByIdDTO(Long id);

    Category findCategoryById(Long id); // used as an "inside" method

    CategoryResponseDTO create(CategoryRequestDTO requestDTO);

    CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO);

    void deleteCategoryById(Long id);

}
