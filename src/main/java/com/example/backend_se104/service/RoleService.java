package com.example.backend_se104.service;

import com.example.backend_se104.entity.model.Role;
import com.example.backend_se104.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

}
