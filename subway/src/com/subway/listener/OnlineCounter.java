package com.subway.listener;

import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener; 
 
//import org.apache.log4j.Logger; 
 
public class OnlineCounter extends HttpServlet implements HttpSessionListener 
    {
   // private static Logger log = Logger.getLogger(OnlineCounter.class);
    private static final long serialVersionUID = 1L;  
    private static int sessionCounter = 1;  
    public OnlineCounter(){  
        //log.info("OnlineCounter initialized.");  
    }  
    public void sessionCreated(HttpSessionEvent se) {
        sessionCounter++;  
        //log.info("session created:" + sessionCounter);  
    }  
    public void sessionDestroyed(HttpSessionEvent se) { 
        sessionCounter--;  
        //log.info("session destroied");  
    }  
    public static int getOnlineSession() {  
        return sessionCounter;  
    } 
 
}