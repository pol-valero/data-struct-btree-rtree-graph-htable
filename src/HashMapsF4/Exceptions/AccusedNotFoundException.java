package HashMapsF4.Exceptions;

public class AccusedNotFoundException extends Exception {
    public AccusedNotFoundException() {
        super("No s'ha trobat cap acusat amb aquest nom.");
    }
}
