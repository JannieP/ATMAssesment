package net.c0nan.suncorp.atm.rest;

import net.c0nan.suncorp.atm.services.dto.ATMDto;
import net.c0nan.suncorp.atm.services.exception.ATMInsufficientDenominationsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ATMCommonExceptionHandler {

    @ExceptionHandler({ATMInsufficientDenominationsException.class})
    public ResponseEntity<ATMDto> handleAllConflicts(Exception ex) {
        ATMDto error = new ATMDto();
        error.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

}
