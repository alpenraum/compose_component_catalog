package com.alpenraum.composecomponentcatalog.ksp.processor

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.io.OutputStream
import kotlin.reflect.KClass

internal operator fun OutputStream.plusAssign(text: String) {
    write(text.toByteArray())
}

internal fun KSFunctionDeclaration.annotationNameParameter(annotationClass: KClass<*>): String {
    val annotation =
        annotations.firstOrNull { it.shortName.asString() == annotationClass.simpleName.toString() }
    val name = annotation?.arguments?.firstOrNull { it.name?.asString() == "name" }
    return name?.value as String
}

internal fun KSFunctionDeclaration.declaration(): String {
    return simpleName.asString()
}

internal fun KSFunctionDeclaration.isValid(): Boolean {
    return this.parameters.isEmpty()
}

fun SymbolProcessorEnvironment.writeMarkdown(
    content: ByteArray,
    fileName: String,
    dependencies: List<KSFile>,
) {
    codeGenerator.createNewFile(
        Dependencies(true, *dependencies.toTypedArray()),
        "reports",
        fileName,
        "md",
    ).also {
        logger.warn("Report can be accessed at ${codeGenerator.generatedFile.first()}")
    }.use {
        it.write(content)
        it.close()
    }
}
