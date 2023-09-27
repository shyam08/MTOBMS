package OTBMS.exception;

public class InvalidPaymentGatewayException extends Exception {
    InvalidPaymentGatewayException() {
        super();
    }

    public InvalidPaymentGatewayException(String message) {
        super(message);
    }

    public InvalidPaymentGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPaymentGatewayException(Throwable cause) {
        super(cause);
    }}
