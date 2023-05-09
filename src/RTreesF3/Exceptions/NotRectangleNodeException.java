package RTreesF3.Exceptions;

public class NotRectangleNodeException extends RuntimeException {

    public NotRectangleNodeException() {
        super("The method you are trying to use is only available for a RectangleNode. You are trying to use it in a HedgeNode.");
    }

}
