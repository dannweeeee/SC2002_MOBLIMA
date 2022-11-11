package Moblima.Exceptions;

/**
 * Exception class for invalid input
 * @author Marcus
 * @version 1.0
 */
public class InvalidInputException extends Exception {
    /**
     * Default constructor for InvalidInputException
     * @param errorMsg errorMessage to be printed
     */
    public InvalidInputException(String errorMsg){
        super(errorMsg);
    }
}
