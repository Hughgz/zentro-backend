package com.ryan.module.catalog.application.interfaces;

import com.ryan.common.exception.core.NotFoundException;
import com.ryan.common.response.PageResponse;
import com.ryan.module.catalog.dtos.request.ProductCreateRequest;
import com.ryan.module.catalog.dtos.response.ProductResponse;

public interface IProductService {
    ProductResponse insertProduct(ProductCreateRequest request) throws Exception;
//    List<ProductResponse> uploadProduct(MultipartFile file);
    PageResponse<ProductResponse> getAllProductWithPaging(int page, int size);
}
