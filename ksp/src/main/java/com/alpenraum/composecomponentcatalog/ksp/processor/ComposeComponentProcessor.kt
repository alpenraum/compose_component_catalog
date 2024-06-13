package com.alpenraum.composecomponentcatalog.ksp.processor

import com.alpenraum.composecomponentcatalog.ksp.annotation.ComposeComponent
import com.alpenraum.composecomponentcatalog.ksp.processor.visitor.ComposeComponentVisitor
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import kotlin.reflect.KClass

internal class ComposeComponentProcessor(
    private val fileGenerator: FileWriter,
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    private val composeComponentVisitor = ComposeComponentVisitor(environment.logger)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val composeComponentFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(ComposeComponent::class)
        if (!composeComponentFunctions.iterator().hasNext()) return emptyList()

        fileGenerator.createFile(composeComponentFunctions, "compose_components_catalog") {
            it.accept(composeComponentVisitor, Unit)
        }

        return (composeComponentFunctions).filterNot { it.validate() }.toList()
    }

    private fun Resolver.findAnnotations(kClass: KClass<*>) =
        getSymbolsWithAnnotation(
            kClass.qualifiedName.toString(),
        )
            .filterIsInstance<KSFunctionDeclaration>().filter {
                it.parameters.isEmpty()
            }
}
