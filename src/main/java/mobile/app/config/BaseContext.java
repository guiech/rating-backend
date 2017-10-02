package mobile.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mongodb.DBObject;

import mobile.app.business.CommentBusiness;
import mobile.app.business.ProductBusiness;
import mobile.app.business.UserBusiness;

public class BaseContext {
	
	@Autowired
	protected ProductBusiness productBusiness;
	
	@Autowired
	protected CommentBusiness commentBusiness;
	
	@Autowired
	protected UserBusiness userBusiness;

	final static Logger LOG = LoggerFactory.getLogger(BaseContext.class);
	
	protected Authentication getAuthInformation() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected void IllegalArgumentExceptionHandler(Exception e) {
		LOG.error("IllegalArgumentException in Controller java class",e);
	}
	
	protected DBObject mapStringToDBObject(String jsonString) {
		return (DBObject) com.mongodb.util.JSON.parse(jsonString);
	}
}
