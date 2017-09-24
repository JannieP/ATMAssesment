package net.c0nan.suncorp.atm.services.listener.event;

import lombok.Data;
import net.c0nan.suncorp.atm.data.ATMDenomination;
import org.springframework.context.ApplicationEvent;

@Data
public class WithdrawalEvent extends ApplicationEvent {

    private ATMDenomination denomination;
    private int quantity;
    private int remainingNotes;

    public WithdrawalEvent(final Object source, final ATMDenomination denomination, final int quantity, final int remainingNotes) {
        super(source);
        this.denomination = denomination;
        this.quantity = quantity;
        this.remainingNotes = remainingNotes;
    }

}
