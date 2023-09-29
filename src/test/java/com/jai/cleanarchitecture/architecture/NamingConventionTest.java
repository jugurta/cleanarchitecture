package com.jai.cleanarchitecture.architecture;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.mapstruct.Mapper;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.jai.cleanarchitecture", importOptions = ImportOption.DoNotIncludeTests.class)
class NamingConventionTest {


    @ArchTest
    static ArchRule useCaseShouldBeSuffixed = classes()
            .that()
            .resideInAPackage("..usecase..")
            .should()
            .haveSimpleNameEndingWith("UseCase");

    @ArchTest
    static ArchRule controllerShouldBeSuffixed = classes()
            .that()
            .resideInAPackage("..presentation.rest.controller..")
            .should()
            .haveSimpleNameEndingWith("Controller");


    @ArchTest
    static ArchRule mapperShouldBeSuffixed = classes()
            .that()
            .areAnnotatedWith(Mapper.class)
            .should()
            .haveSimpleNameEndingWith("Mapper");
}
