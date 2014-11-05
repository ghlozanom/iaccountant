package com.gabloz.iaccountant.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class MobilServlet extends HttpServlet {

	
	private static final String MOBIILE_HOME_PAGE = "mHome.jsp";
	
	private static final long serialVersionUID = -1880623903491041737L;
	private static final Logger logger = Logger
			.getLogger(MobilServlet.class.getCanonicalName());

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.log(Level.INFO, "MobilServlet doGet()");

		UserService userService = UserServiceFactory.getUserService();
		User usuario = userService.getCurrentUser();

		if (usuario == null) {
			response.sendRedirect(userService.createLoginURL(request
					.getRequestURI()));
		} else {

			response.sendRedirect(MOBIILE_HOME_PAGE);
		}
	}


}
