package com.khila.billing.architecture;

import com.enofex.taikai.Taikai;
import com.enofex.taikai.java.JavaConfigurer;
import org.junit.jupiter.api.Test;

class BaselineCodingConventionsTest {

    @Test
    void shouldFollowBasicJavaRules() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .java(java -> java
                        .noUsageOfDeprecatedAPIs()
                        .methodsShouldNotDeclareGenericExceptions()
                        .utilityClassesShouldBeFinalAndHavePrivateConstructor()
                )
                .build()
                .check();
    }

    @Test
    void shouldFollowBasicSpringRules() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .spring(spring -> spring
                        .controllers(controllers -> controllers
                                .shouldBeAnnotatedWithController()
                                .shouldNotDependOnOtherControllers()
                        )
                )
                .build()
                .check();
    }

    @Test
    void shouldFollowNamingConventions() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .java(java -> java
                        .naming(naming -> naming
                                .classesShouldNotMatch(".*Impl")
                                .interfacesShouldNotHavePrefixI()
                                .constantsShouldFollowConventions()
                        )
                )
                .build()
                .check();
    }

    @Test
    void shouldHaveCleanImports() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .java(java -> java
                        .imports(imports -> imports
                                .shouldHaveNoCycles()
                                .shouldNotImport("..internal..")
                                .shouldNotImport("org.junit..")
                        )
                )
                .build()
                .check();
    }


    @Test
    void shouldUseConstructorInjection() {
        Taikai.builder()
                .namespace("com.khila.billing")
                .java(JavaConfigurer::fieldsShouldNotBePublic
                )
                .build()
                .check();
    }

}
