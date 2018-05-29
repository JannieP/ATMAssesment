package net.c0nan.suncorp.atm;

import com.google.common.base.Predicates;
import net.c0nan.suncorp.atm.data.ATMBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"net.c0nan"})
@EnableSwagger2
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

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

}
