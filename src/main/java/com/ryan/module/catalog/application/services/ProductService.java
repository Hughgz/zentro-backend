package com.ryan.module.catalog.application.services;

import com.ryan.common.exception.core.NotFoundException;
import com.ryan.common.response.PageResponse;
import com.ryan.common.utils.NameAliasUtil;
import com.ryan.module.catalog.application.interfaces.IProductService;
import com.ryan.module.catalog.domain.models.Brands;
import com.ryan.module.catalog.domain.models.Categories;
import com.ryan.module.catalog.domain.models.Products;
import com.ryan.module.catalog.domain.repositories.IBrandRepository;
import com.ryan.module.catalog.domain.repositories.ICategoryRepository;
import com.ryan.module.catalog.domain.repositories.IProductRepository;
import com.ryan.module.catalog.dtos.request.ProductCreateRequest;
import com.ryan.module.catalog.dtos.response.ProductResponse;
import com.ryan.module.catalog.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository _repository;
    private final IBrandRepository _bandRepository;
    private final ICategoryRepository _categoryRepository;
    private final ProductMapper _mapper;
    private final NameAliasUtil _nameAliasUtil;

    @Override
    public ProductResponse insertProduct(ProductCreateRequest request) throws Exception {
        Products product = new Products();
        Categories category = _categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new NotFoundException("Category doesn't exist"));
        Brands brand = _bandRepository.findById(request.getBrandId()).orElseThrow(() -> new NotFoundException("Brand doesn't exist"));
        product.setProductId(null);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(request.getBasePrice());
        product.setSlug(_nameAliasUtil.nameAlias(request.getName()));
        product.setBrand(brand);
        product.setCategory(category);
        _repository.save(product);
        return _mapper.toResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> getAllProductWithPaging(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> productsPage = _repository.findAll(pageable);

        List<ProductResponse> response = productsPage
                .getContent()
                .stream()
                .map(_mapper::toResponse)
                .toList();
        return PageResponse.<ProductResponse>builder()
                .page(productsPage.getNumber())
                .size(productsPage.getSize())
                .totalPages(productsPage.getTotalPages())
                .totalItems(productsPage.getTotalElements())
                .hasNext(productsPage.hasNext())
                .hasPrev(productsPage.hasPrevious())
                .items(response).build();
    }
}
