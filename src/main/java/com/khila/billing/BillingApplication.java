package com.khila.billing;

import com.khila.billing.application.config.ApplicationService;
import com.khila.billing.domain.pricing.config.DomainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        basePackages = "com.khila.billing",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainService.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ApplicationService.class)
        })
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

}
