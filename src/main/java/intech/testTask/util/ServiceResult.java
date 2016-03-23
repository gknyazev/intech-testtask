package intech.testTask.util;

/**
 * Utility class to return from service layer
 * @author bn5fg
 */
public class ServiceResult {
	 private static enum State { OK, ERROR };

	    private State state;
	    private String errorMessage;

	    private ServiceResult(State state, String errorMessage)
	    {
	        this.state = state;
	        this.errorMessage = errorMessage;
	    }

	    public static ServiceResult ok()
	    {
	        return new ServiceResult(State.OK, null);
	    }

	    public static ServiceResult error(String errorMessage)
	    {
	        return new ServiceResult(State.ERROR, errorMessage);
	    }

	    public boolean isOK()
	    {
	        return (this.state == State.OK);
	    }

	    public String getErrorMessage()
	    {
	        return errorMessage;
	    }
}
