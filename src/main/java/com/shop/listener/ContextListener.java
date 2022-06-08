package com.shop.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {

		ServletContext sc = event.getServletContext();
		sc.setAttribute("cp", sc.getContextPath());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
