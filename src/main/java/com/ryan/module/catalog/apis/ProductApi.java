package com.ryan.module.catalog.apis;

import com.ryan.common.exception.core.NotFoundException;
import com.ryan.common.response.PageResponse;
import com.ryan.module.catalog.application.interfaces.IProductService;
import com.ryan.common.response.ApiResponse;
import com.ryan.module.catalog.dtos.request.ProductCreateRequest;
import com.ryan.module.catalog.dtos.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.product}")
@AllArgsConstructor
public class ProductApi {
    private final IProductService _service;

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<ProductResponse>>insertProduct(@RequestBody ProductCreateRequest request) throws Exception {
        ProductResponse response = _service.insertProduct(request);
        return ResponseEntity.status(201).body(ApiResponse.success(HttpStatus.OK.value(),"Created product successfully", response));
    }

    @GetMapping("/product")
    public ResponseEntity<ApiResponse<?>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size){
        PageResponse<ProductResponse> responsePage = _service.getAllProductWithPaging(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(), "Successful", responsePage));
    }
}
