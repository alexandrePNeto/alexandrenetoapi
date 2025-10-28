package br.edu.infnet.alexandrenetoapi.exceptions;

public class ClientException extends ContextException {
    public ClientException(String message) {
        super(message, "Client");
    }
}
