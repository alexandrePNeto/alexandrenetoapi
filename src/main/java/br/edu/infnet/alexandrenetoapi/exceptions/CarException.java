package br.edu.infnet.alexandrenetoapi.exceptions;

public class CarException extends ContextException {
    public CarException(String message) {
        super(message, "Car");
    }
}
