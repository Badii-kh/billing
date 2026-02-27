package com.khila.billing.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "com.khila.billing",
        importOptions = {
                ImportOption.DoNotIncludeTests.class
        }
)
public class HexagonalArchitectureTest {

    @ArchTest
    static final Architectures.OnionArchitecture hexagonal_architecture = onionArchitecture()
                // Ref: https://www.archunit.org/userguide/html/000_Index.html#_onion_architecture
                // The domainModels packages contain the domain entities
                .domainModels("com.khila.billing.domain..")
                // The packages in domainServices contains services that use the entities in the domainModel packages
                .domainServices("..port.out..")
                //The applicationServices packages contain services and configuration to run the application and use cases.
                // It can use the items of the domain package but there must not be any dependency from
                // the domain to the application packages.
                .applicationServices("com.khila.billing.application..")
                // The adapter package contains logic to connect to external systems and/or infrastructure.
                // No adapter may depend on another adapter. Adapters can use both the items of the domain and the application
                // packages. Vice versa, neither the domain nor the application packages must contain dependencies
                // on any adapter package.
                .adapter("rest",
                        "com.khila.billing.infrastructure.adapter.in.rest..",
                        "com.khila.billing.infrastructure.adapter.exception.."
                )
                .adapter("persistence", "com.khila.billing.infrastructure.adapter.out.persistence..")
                .adapter("config", "com.khila.billing.infrastructure.adapter.config..");


    @ArchTest
    static final ArchRule domain_should_not_depend_on_spring =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            "org.springframework..",
                            "org.springframework.boot..",
                            "org.springframework.data..",
                            "org.springframework.transaction..",
                            "org.springframework.web.."
                    );

}