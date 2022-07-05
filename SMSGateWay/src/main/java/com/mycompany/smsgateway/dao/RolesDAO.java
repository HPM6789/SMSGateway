/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.entities.Roles;
import com.mycompany.smsgateway.model.RolesModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface RolesDAO {

    //Return a role by id
    public RolesModel getRolesById(Long id);

    //Return a role by name
    public RolesModel getRoleByName(String name);

    //Return all roles in DB
    public List<RolesModel> getAllRoles();

    //Return all roles of a group
    public List<RolesModel> getRolesByGroupId(Long groupId);

    public List<Long> getRoleIdsByGroupId(Long groupId);
    
    public List<String> getRoleNamesByGroupId(Long groupId);

    public List<RolesModel> getRolesByName(String name);

    public List<RolesModel> getRolesByUserId(BigDecimal userId);

    public List<String> getRoleNamesByUserId(BigDecimal userId);

    public int addNewRole(String name, String description, String url);

    public int updateRole(Long id, String name, String description, String url);

    public int deleteRole(Long roleId);

    public RolesModel getRoleByUserAndRoleName(BigDecimal userId, String roleName);

    public List<RolesModel> getRoleNotInGroup(Long groupId);
}
