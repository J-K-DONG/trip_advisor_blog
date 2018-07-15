package com.advisor.trip.util.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class LoggedUserCounter
 *
 */

public class LoggedUserCounter implements HttpSessionListener, HttpSessionAttributeListener {
	
	private static int count = 0 ;
	
    public void sessionCreated(HttpSessionEvent event)  { 
         System.out.println("--------------session created!");
    }


    public void sessionDestroyed(HttpSessionEvent event)  { 
    	System.out.println("---------------session destroy!");
    }


    public void attributeAdded(HttpSessionBindingEvent event)  { 
         if(event.getName().equals("admin") && ((String)event.getValue()).equals("admin")) {
        	 count++;
        	 System.out.println("a user logged in !  now total : " + count);
         }
    }

    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }

}
