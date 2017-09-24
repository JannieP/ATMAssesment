package net.c0nan.base;

import net.c0nan.suncorp.atm.ATMMeta;
import net.c0nan.suncorp.atm.data.ATMBase;
import net.c0nan.suncorp.atm.data.ATMDenomination;

import java.util.Map;

public class ATMTestBase extends ATMBase {
    private static ATMTestBase instance = null;

    private ATMTestBase(Map<ATMDenomination, Integer> quantities) {
        super(quantities);
    }

    public static ATMTestBase getNewInstance(final ATMMeta atmMeta) {
        ATMTestBase.instance = new ATMTestBase(atmMeta.getDefaults());
        return ATMTestBase.instance;
    }
}
