package com.ryan.module.catalog.application.interfaces;

import com.ryan.module.catalog.dtos.request.BrandRequest;
import com.ryan.module.catalog.dtos.response.BrandResponse;

import java.util.List;

public interface IBrandService {
    BrandResponse insertBrand(BrandRequest req);
    List<BrandResponse> getAllBrand();
}
