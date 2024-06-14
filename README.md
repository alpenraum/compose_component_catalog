Compose component catalog
========================

### What is it
This library will generate a markdown page that contains every Composable marked with the `@ComposeComponent` annotation.

Right now it's still pretty rudamentary, it will be extended in the future.
<img width="1398" alt="Screenshot 2024-06-14 at 08 19 41" src="https://github.com/alpenraum/compose_component_catalog/assets/38424979/e14c8ed5-f879-4628-9e8a-74edcd361748">

### How to use it
Get it from Jitpack https://jitpack.io/#alpenraum/compose_component_catalog/.

<details>
<summary>Groovy</summary>

```groovy
dependencies {
    implementation 'com.github.alpenraum:compose_component_catalog:Tag'
    ksp 'com.github.alpenraum:compose_component_catalog:Tag'
	}


```
</details>
<details open>
<summary>Kotlin</summary>

```kotlin
dependencies {
    implementation("com.github.alpenraum:compose_component_catalog:Tag")
    ksp("com.github.alpenraum:compose_component_catalog:Tag")
	}
```
</details>
