package mobile.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseContext {

	final static Logger LOG = LoggerFactory.getLogger(BaseContext.class);
	
	protected Authentication getAuthInformation() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	protected void IllegalArgumentExceptionHandler() {
		LOG.error("IllegalArgumentException in Controller java class");
	}
}
