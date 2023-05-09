package uz.gita.vogayerlib

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.ScreenModelEntryPoint
import dagger.hilt.android.EntryPointAccessors

@Composable
public inline fun <reified T : ScreenModel> Screen.hiltScreenModel(): T {
    val context = LocalContext.current
    return rememberScreenModel {
        val screenModels = EntryPointAccessors
            .fromActivity(context.componentActivity, ScreenModelEntryPoint::class.java)
            .screenModels()

        val impl = T::class.java.getDeclaredAnnotation(ScreenModelImpl::class.java) ?: error("Annotation ScreenModelImpl not found")

        val model = screenModels[impl.value.java]?.get()
            ?: error(
                "${impl.value.java.canonicalName} not found in hilt graph.\nPlease, check if you have a Multibinding " +
                        "declaration to your ScreenModel using @IntoMap and " +
                        "@ScreenModelKey(${T::class.qualifiedName}::class)"
            )
        model as T
    }
}

internal inline fun <reified T> findOwner(context: Context): T? {
    var innerContext = context
    while (innerContext is ContextWrapper) {
        if (innerContext is T) {
            return innerContext
        }
        innerContext = innerContext.baseContext
    }
    return null
}

@PublishedApi
internal val Context.componentActivity: ComponentActivity
    get() = findOwner<ComponentActivity>(this)
        ?: error("Context must be a androidx.activity.ComponentActivity. Current is $this")

@PublishedApi
internal val Context.defaultViewModelProviderFactory: ViewModelProvider.Factory
    get() = componentActivity.defaultViewModelProviderFactory
