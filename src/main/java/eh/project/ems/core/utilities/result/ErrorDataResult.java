package eh.project.ems.core.utilities.result;



public class ErrorDataResult<T> extends DataResult<T>{

	public ErrorDataResult() {
		super(false);
	}
	
	public ErrorDataResult(T data) {
		super(false, data);
	}

	public ErrorDataResult( String message) {
		super(false, message);
	}
	
	public ErrorDataResult(String message, T data) {
		super(false, message, data);
	}
	
}
