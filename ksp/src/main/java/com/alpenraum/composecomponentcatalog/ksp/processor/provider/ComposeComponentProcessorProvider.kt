package com.alpenraum.composecomponentcatalog.ksp.processor.provider

import com.alpenraum.composecomponentcatalog.ksp.processor.ComposeComponentProcessor
import com.alpenraum.composecomponentcatalog.ksp.processor.FileContentGenerator
import com.alpenraum.composecomponentcatalog.ksp.processor.FileWriter
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ComposeComponentProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val fileWriter = FileWriter(FileContentGenerator(), environment)
        return ComposeComponentProcessor(fileWriter, environment)
    }
}
