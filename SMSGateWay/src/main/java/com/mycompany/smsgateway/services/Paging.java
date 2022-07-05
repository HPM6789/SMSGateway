/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.services;

import com.mycompany.smsgateway.model.AuthUserModel;
import com.mycompany.smsgateway.model.CmdcodeListModel;
import com.mycompany.smsgateway.model.CpListModel;
import com.mycompany.smsgateway.model.GroupsModel;
import com.mycompany.smsgateway.model.ShortcodeListModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Minh Hieu Pham
 */
@Service
public class Paging {

    public List<AuthUserModel> userPaging(int start, int end, List<AuthUserModel> users) {
        List<AuthUserModel> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(users.get(i));
        }
        return list;
    }

    public List<GroupsModel> groupPaging(int start, int end, List<GroupsModel> groups) {
        List<GroupsModel> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(groups.get(i));
        }
        return list;
    }

    public List<CpListModel> cpListPaging(int start, int end, List<CpListModel> cplist) {
        List<CpListModel> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(cplist.get(i));
        }
        return list;
    }
    
    public List<ShortcodeListModel> shortcodeListPaging(int start, int end, List<ShortcodeListModel> cplist) {
        List<ShortcodeListModel> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(cplist.get(i));
        }
        return list;
    }
    
    public List<CmdcodeListModel> cmdcodeListPaging(int start, int end, List<CmdcodeListModel> cmds) {
        List<CmdcodeListModel> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(cmds.get(i));
        }
        return list;
    }

    public int[] pageRange(int page, int pageCount) {
        int[] startEnd = new int[2];
        int start = page - 2;
        int end = page + 2;
        if (end > pageCount) {
            start -= (end - pageCount); 
            end = pageCount;
        }
        if (start <= 0) {
            end += ((start - 1) * (-1));
            start = 1;
        }
        end = end > pageCount ? pageCount : end;
        startEnd[0] = start;
        startEnd[1] = end;
        return startEnd;
    }
}
