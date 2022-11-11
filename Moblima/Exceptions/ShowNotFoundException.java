package Moblima.Exceptions;

/**
 * Exception class for Shows not found
 * @author Marcus
 * @version 1.0
 */
public class ShowNotFoundException extends Exception {
    /**
     * Default constructor for ShowNotFoundException
     * @param errorMsg errorMessage to be printed
     */
    public ShowNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
