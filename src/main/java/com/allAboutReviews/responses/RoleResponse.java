package com.allAboutReviews.responses;

import com.allAboutReviews.models.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse extends Response{

    private Role role;
    private List<Role> roleList;

    public RoleResponse(String msg, Role role){
        super(msg);
        this.role=role;
    }

    public RoleResponse(String msg, List<Role> roleList){
        super(msg);
        this.roleList=roleList;
    }
}
