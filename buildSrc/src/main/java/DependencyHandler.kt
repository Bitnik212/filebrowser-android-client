import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.permissionDispatcher() {
    implementation(Library.PERMISSION_DISPATCHER)
    kapt(Library.PERMISSION_DISPATCHER_ANNOTATION_PROCESSOR)
}

fun DependencyHandler.androidX() {
    implementation(Library.ANDROIDX_CORE)
    implementation(Library.ANDROIDX_APPCOMPAT)
}

fun DependencyHandler.compose() {
    implementation(Library.COMPOSE_ACTIVITY)
    implementation(Library.COMPOSE_UI)
    implementation(Library.COMPOSE_MATERIAL)
    implementation(Library.COMPOSE_ACCOMPANIST)
    implementation(Library.COMPOSE_CONSTRAINT)
    implementation(Library.COMPOSE_FLOW_ACCOMPANIST)
    implementation(Library.COMPOSE_PREVIEW)
    implementation(Library.HILT_COMPOSE_NAVIGATION)
}

fun DependencyHandler.hilt() {
    implementation(Library.HILT)
    kapt(Library.HILT_KAPT)
}

fun DependencyHandler.material() {
    implementation(Library.MATERIAL)
}

fun DependencyHandler.splashscreen() {
    implementation(Library.SPLASHSCREEN)
}

fun DependencyHandler.kotlinStd() {
    implementation(Library.KOTLIN_STDLIB)
}

fun DependencyHandler.allModules() {
    Module.ALL.forEach {
        implementationModule(it)
    }
}

fun DependencyHandler.retrofit() {
    implementation(Library.RETROFIT)
    implementation(Library.RETROFIT_SCALARS)
    implementation(Library.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Library.OKHTTP)
    implementation(Library.GSON)
    implementation(Library.GSON_RETROFIT)
}

fun DependencyHandler.apollo() {
    implementation(Library.APOLLO)
    implementation(Library.APOLLO_COROUTINES)
}

fun DependencyHandler.coil() {
    implementation(Library.COIL)
    implementation(Library.COIL_SVG)
}

fun DependencyHandler.lottie() {
    implementation(Library.LOTTIE)
}

fun DependencyHandler.chucker(){
    implementation( Library.CHUCKER)
}

fun DependencyHandler.firebaseConf() {
    implementation(Library.FIREBASE_CONF)
}

fun DependencyHandler.moshi() {
    implementation(Library.MOSHI)
    implementation(Library.MOSHI_KOTLIN)
    kapt(Library.MOSHI_CODEGEN)
}

fun DependencyHandler.serialization() {
    implementation(Library.SERIALIZATION)
    implementation(Library.SERIALIZATION_CONVERTER)
}

fun DependencyHandler.dataStore() {
    implementation(Library.DATA_STORE)
    implementation(Library.DATA_STORE_TYPED)
}

fun DependencyHandler.joda() {
    implementation(Library.JODA)
}

fun DependencyHandler.navigation() {
    implementation(Library.NAVIGATION_UI)
    implementation(Library.NAVIGATION_FRAGMENT)
}

fun DependencyHandler.coroutines() {
    implementation(Library.COROUTINES_CORE)
    implementation(Library.COROUTINES_ANDROID)
}

fun DependencyHandler.implementationModule(moduleName: String) {
    implementation(project(":$moduleName"))
}
fun DependencyHandler.implementationModules(modules: List<String>) {
    modules.forEach {
        implementationModule(it)
    }
}

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
        add("implementation", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
        add("kapt", dependencyNotation)

private fun DependencyHandler.compileOnly(dependencyNotation: Any): Dependency? =
        add("compileOnly", dependencyNotation)

private fun DependencyHandler.project(
        path: String,
        configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
