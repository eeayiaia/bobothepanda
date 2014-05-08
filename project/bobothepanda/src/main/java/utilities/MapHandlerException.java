package utilities;

public class MapHandlerException extends RuntimeException {
	
	public MapHandlerException(){
		super();
	}
	public MapHandlerException(String message){
		super(message);
	}
	public MapHandlerException(Throwable cause){
		super(cause);
	}
}
