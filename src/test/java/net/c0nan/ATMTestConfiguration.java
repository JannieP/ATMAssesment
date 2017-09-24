package net.c0nan;

import net.c0nan.base.ATMTestBase;
import net.c0nan.suncorp.atm.ATMMeta;
import net.c0nan.suncorp.atm.data.ATMBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"net.c0nan"})
public class ATMTestConfiguration {

    @Autowired
    private ATMMeta atmMeta;

    @Bean
    public ATMBase getATMBase() {
        return ATMTestBase.getInstance(atmMeta);
    }

    public static ATMMeta getTest12345Meta() {
        ATMMeta testMeta = new ATMMeta();
        testMeta.setTen(1);
        testMeta.setTwenty(2);
        testMeta.setFifty(3);
        testMeta.setHundred(4);
        return testMeta;
    }

    public static ATMMeta getTest08300Meta() {
        ATMMeta testMeta = new ATMMeta();
        testMeta.setTen(0);
        testMeta.setTwenty(8);
        testMeta.setFifty(3);
        testMeta.setHundred(0);
        return testMeta;
    }

    public static ATMMeta getTestMeta() {
        ATMMeta testMeta = new ATMMeta();
        testMeta.setTen(10);
        testMeta.setTwenty(10);
        testMeta.setFifty(10);
        testMeta.setHundred(10);
        return testMeta;
    }

}
