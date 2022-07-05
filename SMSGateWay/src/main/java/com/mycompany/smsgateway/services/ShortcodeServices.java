/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Minh Hieu Pham
 */
@Service
public class ShortcodeServices {
    
    public boolean compareTwoListShcodeId(List<BigDecimal> list1, List<BigDecimal> list2){
        Collections.sort(list1);
        Collections.sort(list2);
        boolean isEqual = list1.equals(list2);
        return isEqual;
    }
    
}
