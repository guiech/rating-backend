package mobile.app.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException{

	private static final long serialVersionUID = -5330841796195877224L;
	private static final String USER_ALREADY_REGISTERED_MSG = "User is already Register";
	
	public UserAlreadyRegisteredException() {
		super(USER_ALREADY_REGISTERED_MSG);
	}

}
