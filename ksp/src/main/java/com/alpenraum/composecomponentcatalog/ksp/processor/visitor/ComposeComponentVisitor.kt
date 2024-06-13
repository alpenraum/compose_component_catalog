package com.alpenraum.composecomponentcatalog.ksp.processor.visitor

import com.alpenraum.composecomponentcatalog.ksp.annotation.ComposeComponent
import com.alpenraum.composecomponentcatalog.ksp.processor.declaration
import com.alpenraum.composecomponentcatalog.ksp.processor.model.ProcessedFunction
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSEmptyVisitor

internal class ComposeComponentVisitor(private val logger: KSPLogger) :
    KSEmptyVisitor<Unit, ProcessedFunction?>() {
    override fun defaultHandler(
        node: KSNode,
        data: Unit,
    ): ProcessedFunction? {
        return null
    }

    override fun visitFunctionDeclaration(
        function: KSFunctionDeclaration,
        data: Unit,
    ): ProcessedFunction {
        val design =
            function.annotations.firstOrNull {
                it.shortName.asString().contains("${ComposeComponent::class.simpleName}")
            }?.arguments?.firstOrNull {
                it.toString().contains(
                    ComposeComponent::designSource.name,
                )
            }?.value

        return ProcessedFunction(
            function.declaration(),
            design.toString(),
        )
    }
}
