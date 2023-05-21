package RTreesF3.Exceptions;

public class NotHedgeNodeException extends RuntimeException {

    public NotHedgeNodeException() {
        super("The method you are trying to use is only available for a HedgeNode. You are trying to use it in a RectangleNode.");
    }

}
