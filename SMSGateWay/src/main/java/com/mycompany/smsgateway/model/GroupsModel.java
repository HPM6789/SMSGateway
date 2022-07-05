/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public class GroupsModel {
    private Long id;
    
    private String name;
    
    private String description;
    
    private Date createTime;
    
    private Date updateTime;

    private List<RolesModel> roles;
    private List<GroupsModel> groups;
    public GroupsModel() {
    }

    public GroupsModel(Long id) {
        this.id = id;
    }

    public GroupsModel(Long id, String name, String description, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    
    public GroupsModel(Long id, String name, String description, Date createTime, Date updateTime, List<RolesModel> roles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roles = roles;
    }

    
    
    public List<RolesModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesModel> roles) {
        this.roles = roles;
    }

    public List<GroupsModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsModel> groups) {
        this.groups = groups;
    }

    

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
