/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.model;

import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public class RolesModel {
    private Long id;
    
    private String name;
    
    private String description;
    
    private String url;
    
    private List<GroupsModel> groups;

    public RolesModel() {
    }

    public RolesModel(Long id) {
        this.id = id;
    }

    public RolesModel(Long id, String name, String description, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
