package eh.project.ems.core.utilities.result;



public abstract class Result {
	private boolean success;
	private String message;
	
	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	public boolean getResult() {
		return this.success;
	}

}
