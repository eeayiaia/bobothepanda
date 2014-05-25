package utilities;

@SuppressWarnings("serial")
public class MapHandlerException extends Exception {
	
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
