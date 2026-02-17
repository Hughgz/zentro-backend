package com.ryan.module.catalog.application.services;

import com.ryan.common.utils.MapperUtil;
import com.ryan.common.utils.NameAliasUtil;
import com.ryan.module.catalog.application.interfaces.IBrandService;
import com.ryan.module.catalog.domain.models.Brands;
import com.ryan.module.catalog.domain.repositories.IBrandRepository;
import com.ryan.module.catalog.dtos.request.BrandRequest;
import com.ryan.module.catalog.dtos.response.BrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements IBrandService {
    private final IBrandRepository _repository;
    private final MapperUtil _mapper;
    private final NameAliasUtil _nameAliasUtil;
    @Override
    public BrandResponse insertBrand(BrandRequest req) {
        Brands brand = _mapper.convertToEntity(req, Brands.class);
        brand.setSlug(_nameAliasUtil.nameAlias(req.getName()));
        _repository.save(brand);
        return _mapper.convertToDto(brand, BrandResponse.class);
    }

    @Override
    public List<BrandResponse> getAllBrand() {
        List<Brands> brandList = _repository.findAll();
        return brandList.stream().map(b -> _mapper.convertToDto(b, BrandResponse.class)).toList();
    }
}
