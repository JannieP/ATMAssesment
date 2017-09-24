package net.c0nan.suncorp.atm;

import net.c0nan.suncorp.atm.data.ATMBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"net.c0nan"})
public class ATMApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ATMApplication.class, args);
    }

    @Autowired
    private ATMMeta atmMeta;

    @Bean
    public ATMBase getATMBase() {
        return ATMBase.getInstance(atmMeta);
    }

}
