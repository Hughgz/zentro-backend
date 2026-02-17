package com.ryan.module.identity.application.services;

import com.ryan.common.utils.MapperUtil;
import com.ryan.module.identity.application.interfaces.IRoleService;
import com.ryan.module.identity.domain.model.Roles;
import com.ryan.module.identity.domain.repositories.RoleRepository;
import com.ryan.module.identity.dtos.request.RoleRequest;
import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceService implements IRoleService {
    private final RoleRepository _roleRepository;
    private final MapperUtil _mapper;
    public RoleServiceService(RoleRepository roleRepository, MapperUtil mapper) {
        _roleRepository = roleRepository;
        _mapper = mapper;
    }

    @Override
    public void insertRole(RoleRequest request) {
        Roles role = new Roles();
        role.setCode(RoleCodeEnum.USER);
        role.setName(request.getName());
        _roleRepository.save(role);
    }
}
