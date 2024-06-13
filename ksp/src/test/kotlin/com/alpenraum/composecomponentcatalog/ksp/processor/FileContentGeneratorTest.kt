package com.alpenraum.composecomponentcatalog.ksp.processor

import com.alpenraum.composecomponentcatalog.ksp.processor.model.ProcessedFunction
import com.google.devtools.ksp.symbol.KSFunctionDeclaration

class FileContentGeneratorTest {
    private fun createGenerator() = FileContentGenerator()

    private val visitorTransform: (KSFunctionDeclaration) -> ProcessedFunction? = {
        (it as KSFunctionStub).processedFunction
    }
}
