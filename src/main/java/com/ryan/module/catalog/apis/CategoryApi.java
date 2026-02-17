package com.ryan.module.catalog.apis;


import com.ryan.common.exception.core.NotFoundException;
import com.ryan.common.response.ApiResponse;
import com.ryan.module.catalog.application.interfaces.ICategoryService;
import com.ryan.module.catalog.dtos.request.CategoryRequest;
import com.ryan.module.catalog.dtos.response.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryApi {
    private final ICategoryService _service;

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<?>> insertCategory(@RequestBody CategoryRequest request) throws NotFoundException {
        CategoryResponse response = _service.insertCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED.value(),"Created category successfully", response));
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<?>> getAllCateggory() {
        List<CategoryResponse> response = _service.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(),"Successful", response));
    }
}
