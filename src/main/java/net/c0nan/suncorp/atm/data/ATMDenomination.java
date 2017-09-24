package net.c0nan.suncorp.atm.data;

public enum ATMDenomination {
    FIVECENT(0.5),
    TENCENT(0.1),
    TWENTYCENT(0.2),
    FIFTYCENT(0.5),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private double nominalValue;

    ATMDenomination(final double nominalValue) {
        this.nominalValue = nominalValue;
    }

    public double getNominalValue() {
        return nominalValue;
    }

}
