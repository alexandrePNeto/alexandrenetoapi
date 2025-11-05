package br.edu.infnet.alexandrenetoapi.exceptions;

public class ServiceException extends ContextException {
    public ServiceException(String message) {
        super(message, "Service");
    }
}
