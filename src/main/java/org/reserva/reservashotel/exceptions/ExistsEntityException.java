package org.reserva.reservashotel.exceptions;

public class ExistsEntityException extends RuntimeException {
    public ExistsEntityException(String message) {
        super(message);
    }
}
