package net.c0nan.suncorp.atm.data;

import net.c0nan.suncorp.atm.ATMMeta;
import net.c0nan.suncorp.atm.services.ATMUtil;

import java.util.HashMap;
import java.util.Map;

public class ATMBase {

    private Map<ATMDenomination, Integer> quantities;
    private static ATMBase instance = null;

    protected ATMBase(final Map<ATMDenomination, Integer> quantities) {
        this.quantities = quantities;
    }

    public static ATMBase getInstance(final ATMMeta atmMeta) {
        if (ATMBase.instance == null) {
            ATMBase.instance = new ATMBase(atmMeta.getDefaults());
        }
        return ATMBase.instance;
    }

    public boolean addDenominationUnits(final ATMDenomination denomination, final int quantity) {
        int quantiti = getQuantities().get(denomination);
        quantiti += quantity;
        getQuantities().put(denomination, quantiti);
        return true;
    }

    public boolean minusDenominationUnits(final ATMDenomination denomination, final int quantity) {
        int existingQuantity = getQuantities().get(denomination);
        if (existingQuantity < quantity) {
            return false;
        }
        existingQuantity -= quantity;
        getQuantities().put(denomination, existingQuantity);
        return true;
    }

    public int getDenominationQuantity(final ATMDenomination denomination) {
        Integer result = getQuantities().get(denomination);
        return result == null ? 0 : result;
    }

    public double getTotalHolding() {
        return ATMUtil.calculateTotalValue(getQuantities());
    }

    public Map<ATMDenomination, Integer> getDenominationQuantities() {
        return new HashMap<>(quantities);
    }

    private Map<ATMDenomination, Integer> getQuantities() {
        return quantities;
    }
}
