package com.pushyourself11.RestController.ExceptionHandler;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

	/*
	 * public static final Logger LOG =
	 * Logger.getLogger(AccessDeniedExceptionHandler.class);
	 */
	
  @Override
  public void handle(
    HttpServletRequest request,
    HttpServletResponse response, 
    AccessDeniedException exc) throws IOException, ServletException {
      
      Authentication auth 
        = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) {
    	  System.out.println("Please login first");
			/*
			 * LOG.warn("User: " + auth.getName() +
			 * " attempted to access the protected URL: " + request.getRequestURI());
			 */
      }

      response.sendRedirect(request.getContextPath() + "/accessDenied");
  }

}
