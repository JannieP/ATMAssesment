package net.c0nan.suncorp.atm.services;

import net.c0nan.suncorp.atm.data.ATMBase;
import net.c0nan.suncorp.atm.data.ATMDenomination;
import net.c0nan.suncorp.atm.services.dto.ATMDto;
import net.c0nan.suncorp.atm.services.exception.ATMInsufficientDenominationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    private ATMDenominations denominations;
    private ATMBase base;

    @Autowired
    public ATMService(ATMDenominations denominations, ATMBase base) {
        this.denominations = denominations;
        this.base = base;
    }

    public ATMDto withdrawMoney(int amount) {
        ATMDto result = new ATMDto();
        synchronized (this) {
            if (denominations.calculateCombinations(result, amount)) {
                updateBase(result, false);
                result.setMessage(String.format("Transaction successful, %s withdrawn.", new Double(ATMUtil.calculateTotalValue(result.getTheMoney())).toString()));
            } else {
                throw new ATMInsufficientDenominationsException(String.format("Transaction failed, %s not available", amount));
            }
        }
        return result;
    }

    public boolean depositMoney(ATMDto amounts) {
        synchronized (this) {
            updateBase(amounts, true);
        }
        return true;
    }

    public ATMDto getDenominationQuantities() {
        ATMDto dto = new ATMDto();
        dto.setTheMoney(base.getDenominationQuantities());
        return dto;
    }

    public double getTotalHoldings() {
        return base.getTotalHolding();
    }

    private void updateBase(ATMDto result, boolean deposit) {
        for (ATMDenomination denomination : result.getTheMoney().keySet()) {
            Integer value = result.getTheMoney().get(denomination);
            if (value != null) {
                if (deposit) {
                    base.addDenominationUnits(denomination, value);
                } else {
                    base.minusDenominationUnits(denomination, value);
                }
            }
        }
    }

}
