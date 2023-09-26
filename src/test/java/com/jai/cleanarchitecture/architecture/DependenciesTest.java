package com.jai.cleanarchitecture.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependenciesTest {

    @Test
    void doNotCallDeprecatedMethodsFromTheProject() {
        JavaClasses importedClasses = new ClassFileImporter()
                .importPackages("com.jai.cleanarchitecture");
        ArchRule rule = noClasses().should()
                .dependOnClassesThat()
                .areAnnotatedWith(Deprecated.class);
        rule.check(importedClasses);
    }
}
