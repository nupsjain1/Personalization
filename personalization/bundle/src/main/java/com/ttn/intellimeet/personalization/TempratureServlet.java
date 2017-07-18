package com.ttn.intellimeet.personalization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Servlet returns the tempreture in celcius for a particular state
 * 
 * @author nupurjain
 * @see SlingServlet
 * @see SlingSafeMethodsServlet
 */
@SlingServlet(paths = { "/services/statetempreture" })
public class TempratureServlet extends SlingSafeMethodsServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(TempratureServlet.class);
 
	private static Map<String, Double> tempretureMap;
			
	/**
	 * Allows server to handle GET request
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException,
	 *             IOException
	 * @see SlingHttpServletRequest
	 * @see SlingHttpServletResponse
	 */
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.trace("Entered doGet() of TempretureServlet");

		String state = request.getParameter("state");
		response.getWriter().write(tempretureMap.get(state) + "");
	}
	
	@Activate
	protected void activate(final ComponentContext componentContext) {
		tempretureMap = new HashMap<String, Double>();
		tempretureMap.put("delhi", 35.5);
		tempretureMap.put("hp", 10.0);
	}	

}
