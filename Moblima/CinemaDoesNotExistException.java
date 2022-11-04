package Moblima;
public class CinemaDoesNotExistException extends Exception {

    public CinemaDoesNotExistException() {
    }

    public CinemaDoesNotExistException(String message) {
        System.out.println(message);
    }

}