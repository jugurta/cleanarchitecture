package com.jai.cleanarchitecture.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.jai.cleanarchitecture", importOptions = ImportOption.DoNotIncludeTests.class)
class CleanArchitectureTest {

    final static String presentationLayer = "Presentation";
    final static String useCaseLayer = "UseCase";
    final static String domainLayer = "Domain";
    final static String infrastructureLayer = "Infrastructure";


    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringAllDependencies()
            .layer(presentationLayer).definedBy("com.jai.cleanarchitecture.presentation..")
            .layer(useCaseLayer).definedBy("com.jai.cleanarchitecture.usecase..")
            .layer(domainLayer).definedBy("com.jai.cleanarchitecture.domain..")
            .layer(infrastructureLayer).definedBy("com.jai.cleanarchitecture.infrastructure..")

            .whereLayer(presentationLayer).mayNotBeAccessedByAnyLayer()
            .whereLayer(useCaseLayer).mayOnlyBeAccessedByLayers(presentationLayer)
            .whereLayer(infrastructureLayer).mayNotBeAccessedByAnyLayer();


}
