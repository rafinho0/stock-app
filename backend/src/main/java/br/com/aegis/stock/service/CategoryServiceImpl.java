package br.com.aegis.stock.service;

import br.com.aegis.stock.dto.CategoryRequestDTO;
import br.com.aegis.stock.dto.CategoryResponseDTO;
import br.com.aegis.stock.model.Category;
import br.com.aegis.stock.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findCategoryByIdDTO(Long id) {
        Category category = findCategoryById(id);

        return new CategoryResponseDTO(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find Category by id - " + id));
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO requestDTO) {

        Category category = new Category();

        BeanUtils.copyProperties(requestDTO, category);

        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO requestDTO) {

        Category existingCategory = findCategoryById(id);

        BeanUtils.copyProperties(requestDTO, existingCategory);

        Category updateCategory = categoryRepository.save(existingCategory);

        return new CategoryResponseDTO(updateCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {

        if(!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category id not found");
        }

        categoryRepository.deleteById(id);
    }
}
