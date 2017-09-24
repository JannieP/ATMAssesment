package net.c0nan.suncorp.atm.rest;

import net.c0nan.suncorp.atm.services.ATMService;
import net.c0nan.suncorp.atm.services.dto.ATMDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm/admin")
public class ATMAdminController {

    @Autowired
    private ATMService atmService;

    @RequestMapping(value = "/quantities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ATMDto getQuantities() {
        return atmService.getDenominationQuantities();
    }

    @RequestMapping(value = "/totalholdings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getTotalValue() {
        return atmService.getTotalHoldings();
    }

}
