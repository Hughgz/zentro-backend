package com.ryan.common.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public <D, E> D convertToDto(E entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }
    public <E, D> E convertToEntity(D dto, Class<E> entityClass){
        return modelMapper.map(dto, entityClass);
    }
}
