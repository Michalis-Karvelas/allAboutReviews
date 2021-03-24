package com.allAboutReviews.controllers;

import com.allAboutReviews.models.Role;
import com.allAboutReviews.requests.RoleRequest;
import com.allAboutReviews.responses.Response;
import com.allAboutReviews.responses.RoleResponse;
import com.allAboutReviews.services.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RequestMapping(value="/api/role")
@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    // list of all the roles
    @GetMapping(value="/getall")
    public RoleResponse getAll(){
        log.info("Ready to find all the roles");
        return new RoleResponse("Found all the roles", roleServiceImpl.getAll());
    }

    //get role by the given id
    @GetMapping(value = "/getbyid/{roleId}")
    public RoleResponse getById(@PathVariable Long roleId){
        log.info("Ready to find the role with id: {}", roleId);
        return new RoleResponse("Found the role", roleServiceImpl.getById(roleId));
    }

    //create a new role
    @PostMapping(value = "/new",consumes = "application/json", produces = "application/json")
        public Response CreateNewRole(@RequestBody RoleRequest request){
        Role role=new Role(request.getType());
        roleServiceImpl.newRole(request);
        log.info("The role has been saved");
        return new Response("The role has been inserted in the DB");
    }

    //update an existing role
    @PutMapping(value = "/update/{roleId}",consumes = "application/json", produces = "application/json")
    public Response updateExistingRole(@PathVariable(value = "roleId") Long roleId,@RequestBody RoleRequest request){
        log.info("Ready to update the role with id: {}",roleId);
        Role role= roleServiceImpl.updateRole(roleId,request);
        if(isNull(role)){
            return new Response("The role does not exist. It can't be updated");
        }
        return new Response("The role has been updated successfully");
    }

    //delete an existing role
    @DeleteMapping(value = "/delete/{roleId}")
    public Response deleteRole(@PathVariable(value = "roleId") Long roleId) {
        log.info("Ready to delete a role");
        if (roleServiceImpl.deleteRole(roleId) == false) {
            return new Response("The role could not be deleted");
        }
        return new Response("The role has been deleted successfully");
    }

}
