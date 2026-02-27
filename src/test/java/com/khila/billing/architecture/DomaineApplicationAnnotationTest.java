package com.khila.billing.architecture;

import com.enofex.taikai.Taikai;
import com.khila.billing.application.config.ApplicationService;
import com.khila.billing.domain.pricing.config.DomainService;
import org.junit.jupiter.api.Test;

class DomaineApplicationAnnotationTest {

    @Test
    void domainServiceShouldBeImplementedInInfrastructurePackage() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .java(java -> java
                        .classesAnnotatedWithShouldResideInPackage(DomainService.class, "..infrastructure..")
                        .classesShouldBeAnnotatedWith(".*Adapter", DomainService.class)
                )
                .build()
                .check();
    }

    @Test
    void applicationServiceShouldBeImplementedInApplicationPackage() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .excludeClasses("com.khila.billing.domain.pricing.config.DomainService", "com.khila.billing.application.config.ApplicationService")
                .java(java -> java
                        .classesAnnotatedWithShouldResideInPackage(ApplicationService.class, "..application..")
                        .classesShouldBeAnnotatedWith(".*Service", ApplicationService.class)
                )
                .build()
                .check();
    }

}
