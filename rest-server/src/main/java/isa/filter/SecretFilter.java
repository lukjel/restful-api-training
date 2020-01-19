package isa.filter;


import isa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;

@WebFilter("/api/secret/*")
public class SecretFilter implements javax.servlet.Filter {

	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Initialization of secret filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long start = Instant.now().toEpochMilli();
		if (request instanceof HttpServletRequest) {
			log.debug("Checking auth-token in header!");
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String authToken = httpRequest.getHeader("auth-token");
			if ((authToken!=null) && (authToken.length()>16)) {
				log.debug("Token exists - continue processing");
				chain.doFilter(request, response);
			} else {
				log.debug("Missing auth-token in headers for SECRET requests! No process!");
			}
		} else {
			log.debug("It is not HttpServletRequest!");
		}
		long duration = Instant.now().toEpochMilli() - start;
		log.debug("It took {}ms.", duration);
	}

	@Override
	public void destroy() {
		log.debug("Destroy filter");
	}
}
