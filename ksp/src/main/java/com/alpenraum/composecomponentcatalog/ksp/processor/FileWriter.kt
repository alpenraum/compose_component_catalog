package com.alpenraum.composecomponentcatalog.ksp.processor

import com.alpenraum.composecomponentcatalog.ksp.processor.model.ProcessedFunction
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSFunctionDeclaration

internal class FileWriter(
    private val fileContentGenerator: FileContentGenerator,
    private val environment: SymbolProcessorEnvironment,
) {
    fun createFile(
        functions: Sequence<KSFunctionDeclaration>,
        fileName: String,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
    ) {
        if (!functions.iterator().hasNext()) {
            return
        }

        val files = functions.toList().mapNotNull { it.containingFile }

        val content =
            fileContentGenerator.generateContent(
                functions.toList(),
                visitorTransform,
            )

        environment.writeMarkdown(content.toByteArray(), fileName, files)
    }
}
