package com.khila.billing.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "com.khila.billing",
        importOptions = {
                ImportOption.DoNotIncludeTests.class
        }
)
public class DomainPurityTest {

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

    @ArchTest
    static final ArchRule domain_should_not_be_annotated_with_spring =
            noClasses().that().resideInAPackage("..domain..")
                    .should().beAnnotatedWith("org.springframework.stereotype.Component")
                    .orShould().beAnnotatedWith("org.springframework.stereotype.Service")
                    .orShould().beAnnotatedWith("org.springframework.stereotype.Repository")
                    .orShould().beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired")
                    .orShould().beAnnotatedWith("org.springframework.transaction.annotation.Transactional");


    // 1) Pas de dépendance aux packages Hibernate et JPA
    @ArchTest
    static final ArchRule domain_should_not_depend_on_hibernate_or_jpa =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            "org.hibernate..",
                            "org.hibernate.annotations..",
                            "jakarta.persistence..",
                            "javax.persistence.."       // si legacy
                    );

    @ArchTest
    static final ArchRule domain_should_not_be_annotated_with_jpa_or_hibernate =
            noClasses().that().resideInAPackage("..domain..")
                    // Annotations JPA courantes
                    .should().beAnnotatedWith("jakarta.persistence.Entity")
                    .orShould().beAnnotatedWith("jakarta.persistence.Embeddable")
                    .orShould().beAnnotatedWith("jakarta.persistence.MappedSuperclass")
                    .orShould().beAnnotatedWith("jakarta.persistence.Table")
                    .orShould().beAnnotatedWith("jakarta.persistence.Id")
                    .orShould().beAnnotatedWith("jakarta.persistence.GeneratedValue")
                    .orShould().beAnnotatedWith("jakarta.persistence.Column")
                    .orShould().beAnnotatedWith("jakarta.persistence.OneToOne")
                    .orShould().beAnnotatedWith("jakarta.persistence.OneToMany")
                    .orShould().beAnnotatedWith("jakarta.persistence.ManyToOne")
                    .orShould().beAnnotatedWith("jakarta.persistence.ManyToMany")
                    .orShould().beAnnotatedWith("javax.persistence.Entity")
                    .orShould().beAnnotatedWith("javax.persistence.Embeddable")
                    .orShould().beAnnotatedWith("javax.persistence.MappedSuperclass")
                    .orShould().beAnnotatedWith("javax.persistence.Table")
                    .orShould().beAnnotatedWith("javax.persistence.Id")
                    .orShould().beAnnotatedWith("javax.persistence.GeneratedValue")
                    .orShould().beAnnotatedWith("javax.persistence.Column")
                    .orShould().beAnnotatedWith("javax.persistence.OneToOne")
                    .orShould().beAnnotatedWith("javax.persistence.OneToMany")
                    .orShould().beAnnotatedWith("javax.persistence.ManyToOne")
                    .orShould().beAnnotatedWith("javax.persistence.ManyToMany");

    @ArchTest
    static final ArchRule domain_should_not_refer_to_hibernate_specific_exceptions =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAnyPackage(
                            "org.hibernate.exception.."
                    );


}