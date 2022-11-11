package Moblima.Exceptions;

/**
 * Exception class for Seats not available
 * @author Marcus
 * @version 1.0
 */
public class SeatsNotAvailableException extends Exception{
    /**
     * Default constructor for SeatsNotAvailableException
     * @param errorMsg errorMessage to be printed
     */
    public SeatsNotAvailableException(String errorMsg){
        super(errorMsg);
    }
}
