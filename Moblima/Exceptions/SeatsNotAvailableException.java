package Moblima.Exceptions;

public class SeatsNotAvailableException extends Exception{
    public SeatsNotAvailableException(String errorMsg){
        super(errorMsg);
    }
}
