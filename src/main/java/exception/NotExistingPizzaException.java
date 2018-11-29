package exception;

public class NotExistingPizzaException extends StockageException {
	
	public NotExistingPizzaException(String message) {
		super(message);
	}
}
