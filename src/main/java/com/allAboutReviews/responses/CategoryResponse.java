package com.allAboutReviews.responses;

import com.allAboutReviews.models.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse extends Response{

    private Category category;
    private List<Category> categories;

    public CategoryResponse(String msg, Category category){
        super(msg);
        this.category=category;
    }

    public CategoryResponse(String msg, List<Category> categories){
        super(msg);
        this.categories=categories;
    }
}
