package com.allAboutReviews.services;

import com.allAboutReviews.models.Category;
import com.allAboutReviews.repository.CategoryRepository;
import com.allAboutReviews.requests.CategoryRequest;
import com.allAboutReviews.services.interfaces.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        log.info("Ready to find all the categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long categoryId) {
        log.info("Ready to find the category with id:{}",categoryId);
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public boolean newCategory(CategoryRequest request) {
        log.info("Ready to create a new category");
        Category tempCategory=new Category(request.getTypeOfCategory());
        Category newCategory=categoryRepository.save(tempCategory);
        log.info("The new category is {}", newCategory);
        log.info("The category has been inserted in the DB");
        return true;
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryRequest request) {

        log.info("Ready to update the category with id:{}", categoryId);
        Category existingCategory=categoryRepository.findById(categoryId).orElse(null);
        if (isNull(existingCategory)){
            return null;
        }
        existingCategory.setTypeOfCategory(request.getTypeOfCategory());
        Category updatedCategory=categoryRepository.save(existingCategory);
        log.info("The updated category is {}", updatedCategory);
        log.info("The updated category has been inserted in the DB");
        return updatedCategory;
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        log.info("Ready to delete the category with id {}",categoryId);
        if (categoryRepository.existsById(categoryId)){
            categoryRepository.deleteById(categoryId);
            log.info("The category with the given id:{} has been successfully deleted",categoryId);
            return true;
        }
        log.info("There is no category with id {}", categoryId);
        return false;
    }
}
