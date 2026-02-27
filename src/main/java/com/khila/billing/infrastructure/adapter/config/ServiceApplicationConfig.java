package com.khila.billing.infrastructure.adapter.config;

import com.khila.billing.application.config.ApplicationService;
import com.khila.billing.domain.pricing.config.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.khila.billing",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainService.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ApplicationService.class)
        })
public class ServiceApplicationConfig {
}
