package br.com.aegis.stock.service;

import br.com.aegis.stock.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }
}
