package com.allAboutReviews.services;

import com.allAboutReviews.models.Role;
import com.allAboutReviews.repository.RoleRepository;
import com.allAboutReviews.requests.RoleRequest;
import com.allAboutReviews.services.interfaces.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        log.info("Ready to find all the roles");
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long roleId) {
        log.info("Ready to find the role with id: {}",roleId);
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public boolean newRole(RoleRequest request) {
        log.info("Ready to create a new role");
        Role tempRole= new Role(request.getType());
        Role newRole=roleRepository.save(tempRole);
        log.info("The new role is: {}",newRole);
        log.info("The new role has been inserted in the DB");
        return true;
    }

    @Override
    public Role updateRole(Long roleId, RoleRequest request) {
        log.info("Ready to update the role with id: {}",roleId);
        Role existingRole=roleRepository.findById(roleId).orElse(null);
        if (isNull(existingRole)){
            return null;
        }
        existingRole.setType(request.getType());
        Role updatedRole=roleRepository.save(existingRole);
        log.info("The updated role is {}", updatedRole);
        log.info("The updated role has been inserted to the DB");
        return updatedRole;
    }

    @Override
    public boolean deleteRole(Long roleId) {
        log.info("Ready to delete the role with id: {}",roleId);
        if (roleRepository.existsById(roleId)){
            roleRepository.deleteById(roleId);
            log.info("The role has been deleted successfully");
            return true;
        }
        log.info("The role has not been deleted successfully");
        return  false;
    }
}
