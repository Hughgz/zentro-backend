package com.ryan.module.catalog.application.services;

import com.ryan.common.exception.core.NotFoundException;
import com.ryan.common.utils.MapperUtil;
import com.ryan.common.utils.NameAliasUtil;
import com.ryan.module.catalog.application.interfaces.ICategoryService;
import com.ryan.module.catalog.domain.models.Categories;
import com.ryan.module.catalog.domain.models.Products;
import com.ryan.module.catalog.domain.repositories.ICategoryRepository;
import com.ryan.module.catalog.domain.repositories.IProductRepository;
import com.ryan.module.catalog.dtos.request.CategoryRequest;
import com.ryan.module.catalog.dtos.response.CategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository _repository;
    private final MapperUtil _mapper;
    private final IProductRepository _productRepository;
    private final NameAliasUtil _nameAlias;
    @Override
    public CategoryResponse insertCategory(CategoryRequest req) throws NotFoundException {
        Categories category = _mapper.convertToEntity(req,Categories.class);
//        Products product = _productRepository.findById(req.getParentId()).orElseThrow(() -> new NotFoundException("Product not found"));
        category.setSlug(_nameAlias.nameAlias(req.getName()));
        category.setParentId(null);
        _repository.save(category);
        return _mapper.convertToDto(category, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Categories> categoriesList = _repository.findAll();
        return categoriesList.stream().map(c -> _mapper.convertToDto(c, CategoryResponse.class)).toList();
    }
}
