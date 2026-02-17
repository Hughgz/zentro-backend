package com.ryan.module.catalog.apis;

import com.ryan.common.response.ApiResponse;
import com.ryan.module.catalog.application.interfaces.IBrandService;
import com.ryan.module.catalog.dtos.request.BrandRequest;
import com.ryan.module.catalog.dtos.response.BrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.brand}")
@AllArgsConstructor
public class BrandApi {
    private final IBrandService _service;

    @PostMapping("/brand")
    public ResponseEntity<ApiResponse<?>> insertBrand(@RequestBody BrandRequest request){
        BrandResponse response = _service.insertBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(HttpStatus.CREATED.value(),"Create brand successfully", response));
    }
    @GetMapping("/brand")
    public ResponseEntity<ApiResponse<?>> getAllBrands(){
        List<BrandResponse> response = _service.getAllBrand();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(),"Successful", response));
    }
}
