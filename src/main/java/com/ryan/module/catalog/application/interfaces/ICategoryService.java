package com.ryan.module.catalog.application.interfaces;

import com.ryan.common.exception.core.NotFoundException;
import com.ryan.module.catalog.dtos.request.CategoryRequest;
import com.ryan.module.catalog.dtos.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse insertCategory(CategoryRequest req) throws NotFoundException;
    List<CategoryResponse> getAllCategory();
}
