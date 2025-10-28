package br.edu.infnet.alexandrenetoapi.exceptions;

public class AttendantException extends ContextException {
    public AttendantException(String message) {
        super(message, "Attendant");
    }
}
