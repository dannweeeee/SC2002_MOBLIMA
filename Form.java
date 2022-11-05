package Moblima;
/**
 * Base class for user interfaces.
 * @author Nghia Nguyen
 * @version 1.0
 */
public abstract class Form {
    protected boolean exitFlag = false;
    /**
     * Shows the form.
     * @return an object representing the result of the form. This differs from form to form.
     */
    public abstract Object show();

    /**
     * Exits the form.
     */
    protected void exit() {
        exitFlag = true;
    }
}
