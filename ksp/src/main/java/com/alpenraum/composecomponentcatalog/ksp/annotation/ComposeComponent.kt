package com.alpenraum.composecomponentcatalog.ksp.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class ComposeComponent(
    val designSource: String = "",
)
