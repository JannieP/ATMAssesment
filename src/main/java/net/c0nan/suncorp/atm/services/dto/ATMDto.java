package net.c0nan.suncorp.atm.services.dto;

import lombok.Data;
import net.c0nan.suncorp.atm.data.ATMDenomination;

import java.util.HashMap;
import java.util.Map;

@Data
public class ATMDto {
    private String message;
    private Map<ATMDenomination, Integer> theMoney = new HashMap<>();

    public ATMDto reset() {
        theMoney = new HashMap<>();
        return this;
    }
}
