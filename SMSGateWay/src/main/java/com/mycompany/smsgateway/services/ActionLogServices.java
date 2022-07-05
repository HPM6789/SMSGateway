/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smsgateway.services;

import com.mycompany.smsgateway.dao.ActionLogDAO;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Minh Hieu Pham
 */
@Service
public class ActionLogServices {

    private static final String[] IP_HEADER_CANDIDATES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"};

    @Autowired
    private ActionLogDAO dao;
    
    public String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public String getOSName(String browseDetails) {
        String os = null;
        String user = browseDetails.toLowerCase();
        if (user.contains("windows")) {
            os = "Windows";
        } else if (user.contains("mac")) {
            os = "Mac";
        } else if (user.contains("x11")) {
            os = "Unix";
        } else if (user.contains("android")) {
            os = "Android";
        } else if (user.contains("iphone")) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + browseDetails;
        }
        return os;
    }

    public String getAppName(String browseDetails) {
        String browser = null;
        
        String user = browseDetails.toLowerCase();
        if (user.contains("msie")) {
            String substring = browseDetails.substring(browseDetails.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (browseDetails.substring(browseDetails.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" + (browseDetails.substring(browseDetails.indexOf("Version"))
                            .split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("operabrowseDetails")) {
                browser = (browseDetails.substring(browseDetails.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        + "-" + (browseDetails.substring(browseDetails.indexOf("Version")).split(" ")[0])
                                .split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((browseDetails.substring(browseDetails.indexOf("OPR")).split(" ")[0])
                        .replace("/", "-")).replace("OPR", "Opera");
            }
        } else if (user.contains("chrome")) {
            browser = (browseDetails.substring(browseDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
                || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
                || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(browseDetails.substring(browseDetails.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (browseDetails.substring(browseDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else {
            browser = "UnKnown, More-Info: " + browseDetails;
        }
        return browser;
    }

    public String getOSNameByUserAgent(String browseDetails) {
        UserAgent userAgent = UserAgent.parseUserAgentString(browseDetails);
        OperatingSystem agent = userAgent.getOperatingSystem();
        return agent.getName();
    }
    
    public String getClientDeviceName(String browseDetails) {
        UserAgent userAgent = UserAgent.parseUserAgentString(browseDetails);
        OperatingSystem agent = userAgent.getOperatingSystem();
        return agent.getDeviceType().getName();
    }
    
    public String getAppNameByUserAgent(String browseDetails) {
        UserAgent userAgent = UserAgent.parseUserAgentString(browseDetails);
        return userAgent.getBrowser().getName();
    }
    
    public int logAction(BigDecimal userIdDec, String actionlogName, 
            String actionlogObjectType, BigInteger actionlogObjectId,  
            String actionlogResult, String actionlogDesc, String actionlogData, String actionlogMsisdn,
            HttpServletRequest request) {
        System.out.println("Log Action");
        ActionLogServices actionLogServices = new ActionLogServices();
        String browserDetails = request.getHeader("User-Agent");
        String os = actionLogServices.getOSNameByUserAgent(browserDetails);
        String browser = actionLogServices.getAppNameByUserAgent(browserDetails);
        String IP = actionLogServices.getClientIpAddress(request);
        String device = actionLogServices.getClientDeviceName(browserDetails);
        
        System.out.println("os: " + os + ", browser: " + browser + ", IP:" + IP + ", Device: " + device);
        int rows = dao.addUserAction(userIdDec.toBigInteger(), actionlogName, actionlogObjectType, 
                actionlogObjectId, IP, device, os, browser, 
                actionlogResult, actionlogDesc, actionlogData, actionlogMsisdn);
        return rows;
    }
    
    public static void main(String[] args) {
        ActionLogServices a = new ActionLogServices();

    }
}
