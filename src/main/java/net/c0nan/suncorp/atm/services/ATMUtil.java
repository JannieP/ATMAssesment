package net.c0nan.suncorp.atm.services;

import net.c0nan.suncorp.atm.data.ATMDenomination;

import java.util.Map;

public class ATMUtil {

    public static double calculateTotalValue(Map<ATMDenomination, Integer> notes) {
        double total = 0;
        for (ATMDenomination denomination : notes.keySet()) {
            total += (notes.get(denomination) * denomination.getNominalValue());
        }
        return total;
    }

}
