package com.allAboutReviews.controllers;

import com.allAboutReviews.models.Category;
import com.allAboutReviews.requests.CategoryRequest;
import com.allAboutReviews.responses.CategoryResponse;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.services.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
//@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    // list of all the categories
    @GetMapping(value="/getall")
    public CategoryResponse getAll(){
        log.info("Ready to find all the categories");
        return new CategoryResponse("Found all the categories", categoryServiceImpl.getAll());
    }

    // get category by id
    @GetMapping(value="/getbyid/{categoryId}")
    public CategoryResponse getById(@PathVariable Long categoryId){
        log.info("Ready to find category by id:{} ", categoryId);
        return new CategoryResponse("Found the category",categoryServiceImpl.getById(categoryId));
    }

    //create new category
    @PostMapping(value="/new",consumes = "application/json",
            produces = "application/json")
    public Response createNewCategory(@RequestBody CategoryRequest request){
        log.info("Ready to create a new category");
        categoryServiceImpl.newCategory(request);
        log.info("The category has been saved");
        return new Response("The category has been saved");
    }

    //update an existing category
    @PutMapping(value="/update/{categoryId}",consumes = "application/json",produces = "application/json")
    public Response updateExistingCategory(@PathVariable(value = "categoryId") Long categoryId,@RequestBody CategoryRequest request){
        log.info("Ready to update a category");
        Category category = categoryServiceImpl.updateCategory(categoryId, request);
        if(isNull(category)){
            return new Response("The category does not exist. It can't be updated");
        }
        return new Response("The category has been updated successfully");
    }

    //delete an existing category
    @DeleteMapping(value = "/delete/{categoryId}")
    public Response deleteCategory(@PathVariable(value = "categoryId") Long categoryId){
        log.info("Ready to delete the category with id:{} ",categoryId);
        if(categoryServiceImpl.deleteCategory(categoryId) == false){
            return new Response("The category couldn't be deleted");
        }
        return new Response("Category deleted successfully");
    }
}
