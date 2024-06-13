package com.alpenraum.composecomponentcatalog.ksp.processor

import com.alpenraum.composecomponentcatalog.ksp.processor.model.ProcessedFunction
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import net.steppschuh.markdowngenerator.link.Link
import net.steppschuh.markdowngenerator.rule.HorizontalRule
import net.steppschuh.markdowngenerator.text.Text
import net.steppschuh.markdowngenerator.text.heading.Heading

internal class FileContentGenerator {
    fun generateContent(
        functions: List<KSFunctionDeclaration>,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
    ): String {
        return generateTexts(functions, visitorTransform)
    }

    private fun generateTexts(
        functions: List<KSFunctionDeclaration>,
        visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction?,
    ): String {
        return buildString {
            append(Heading("Compose Components", Heading.MINIMUM_LEVEL)).newLine()

            if (functions.isEmpty()) return@buildString

            val processedFunctions =
                functions.mapNotNull(visitorTransform)

            processedFunctions.map {
                appendComponentBlock(it)
            }
        }
    }

    private fun StringBuilder.appendComponentBlock(function: ProcessedFunction) {
        append(Heading(function.name, 3)).newLine()
        function.designSource?.let {
            if (it.contains("http")) {
                append("* ").append(Text("\tDesign: ")).append(Link(it, it)).newLine()
            } else {
                append("* ").append(Text("\tDesign: ")).append(Text(it)).newLine()
            }
            append(HorizontalRule()).newLine()
        }
    }
}

private fun StringBuilder.newLine() = append("\n")
