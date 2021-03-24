package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Category;
import com.allAboutReviews.requests.CategoryRequest;

import java.util.List;

public interface ICategoryService {

    //list of all categories
    List<Category> getAll();

    //get category by the given id
    Category getById(Long categoryId);

    //create a new category
    boolean newCategory(CategoryRequest category);

    //update an existing category with the given id
    Category updateCategory(Long categoryId,CategoryRequest category);

    //delete the category with the given id
    boolean deleteCategory(Long categoryId);
}
