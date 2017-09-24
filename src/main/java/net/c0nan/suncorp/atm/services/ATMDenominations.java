package net.c0nan.suncorp.atm.services;

import net.c0nan.suncorp.atm.data.ATMBase;
import net.c0nan.suncorp.atm.data.ATMDenomination;
import net.c0nan.suncorp.atm.services.dto.ATMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;

@Service
public class ATMDenominations {

    private ATMBase base;

    @Autowired
    public ATMDenominations(final ATMBase base) {
        this.base = base;
    }

    public boolean calculateCombinations(final ATMDto combination, final double amount) {
        return calculateCombinations(combination, amount, 0);
    }

    private boolean calculateCombinations(final ATMDto combination, final double amount, final Integer activeIndex) {

        double effectiveAmount = amount;

        ATMDenomination[] denominations = getDecendingSortedDenominations();
        ATMDenomination denomination = denominations[activeIndex];

        double matchableAmount = effectiveAmount - (effectiveAmount % denomination.getNominalValue());
        int unitsRequired = (int) (matchableAmount / denomination.getNominalValue());
        int unitsAvailable = base.getDenominationQuantity(denomination);
        int effectiveUnits = unitsAvailable < unitsRequired ? unitsAvailable : unitsRequired;

        if (effectiveUnits > 0) {
            for (int i = effectiveUnits; i > 0; i--) {

                combination.getTheMoney().put(denomination, i);
                effectiveAmount -= denomination.getNominalValue() * i;

                if (effectiveAmount > 0 && activeIndex == denominations.length - 1) {
                    return false;
                } else if (effectiveAmount == 0 || calculateCombinations(combination, effectiveAmount, activeIndex + 1)) {
                    return true;
                }

                combination.getTheMoney().remove(denomination);
                effectiveAmount += denomination.getNominalValue() * i;

            }
            return false;
        } else {
            if (effectiveAmount > 0 && activeIndex == denominations.length - 1) {
                return false;
            } else {
                return calculateCombinations(combination, effectiveAmount, activeIndex + 1);
            }
        }

    }

    private ATMDenomination[] getDecendingSortedDenominations() {
        ATMDenomination[] denominations = ATMDenomination.values();
        Arrays.sort(denominations, new DecendingDenominationComparator());
        return denominations;
    }

    class DecendingDenominationComparator implements Comparator<ATMDenomination> {
        @Override
        public int compare(final ATMDenomination a1, final ATMDenomination a2) {
            return Double.compare(a2.getNominalValue(), a1.getNominalValue());
        }
    }

}
