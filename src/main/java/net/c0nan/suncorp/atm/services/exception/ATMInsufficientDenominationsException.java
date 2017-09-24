package net.c0nan.suncorp.atm.services.exception;

public class ATMInsufficientDenominationsException extends RuntimeException {

    public ATMInsufficientDenominationsException(final String message) {
        super(message);
    }

}
