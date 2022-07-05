/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.smsgateway.dao;

import com.mycompany.smsgateway.entities.Groups;
import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.GroupsModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Minh Hieu Pham
 */
public interface GroupDAO {
    
    //Return groups this user is joining in
    public List<GroupsModel> getGroupByUserId(BigDecimal userID);
    
    public List<GroupsModel> getAllGroups();
    
    public List<GroupsModel> getGroupsByName(String groupName);
    
    public Long getNewestGrId();
    
    public int addNewGroup(String name, String description);
    
    public int updateGroup(Long id, String name, String description);
    
    public GroupsModel getGroupByName(String groupName);
    
    public GroupsModel getGroupById(Long id);
    
    public int addMemberForGroup(Long id, List<BigDecimal> members);
    
    public int removeMemberForGroup(Long id, List<BigDecimal> membersId);
    
    public int addRoleForGroup(Long groupId, List<Long> roleId);
    
    public int removeRoleForGroup(Long groupId, List<Long> roleId);
    
    public int deleteGroupRoleByRoleId(Long roleId);
    
    public int deleteGroupRoleByGroupId(Long roleId);
    
    public int deleteGroupUserByUserId(Long roleId);
    
    public int deleteGroupUserByGroupId(Long groupId);
    
    public int deleteGroup(Long groupId);
}
