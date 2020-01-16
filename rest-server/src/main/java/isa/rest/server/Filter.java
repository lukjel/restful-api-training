package isa.rest.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.Instant;

@WebFilter("/api/*")
public class Filter implements javax.servlet.Filter {

	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Initialization of filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long start = Instant.now().toEpochMilli();
		log.debug("Filtering!");
		chain.doFilter(request, response);
		long duration = Instant.now().toEpochMilli() - start;
		log.debug("It took {}ms.", duration);
	}

	@Override
	public void destroy() {
		log.debug("Destroy filter");
	}
}
