package com.allAboutReviews.services.interfaces;

import com.allAboutReviews.models.Role;
import com.allAboutReviews.requests.RoleRequest;

import java.util.List;

public interface IRoleService {

    // list of all roles
    List<Role> getAll();

    // get role by id
    Role getById(Long roleId);

    // new role
    boolean newRole(RoleRequest request);

    //update role(by id)
    Role updateRole(Long roleId, RoleRequest request);

    // delete role(by id)
    boolean deleteRole(Long roleId);
}
