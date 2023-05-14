package uz.gita.vogayerlib

import cafe.adriel.voyager.core.model.ScreenModel
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenModelImpl(val value: KClass<out ScreenModel>)
