/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Minh Hieu Pham
 */
@Service
public class ParseDataTypeService {
    
    public Date parseToDate(String strDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formater.parse(strDate);
            return date;
        } catch (Exception ex) {
            Logger.getLogger(ParseDataTypeService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void main(String[] args) {
        
        
    }
}
