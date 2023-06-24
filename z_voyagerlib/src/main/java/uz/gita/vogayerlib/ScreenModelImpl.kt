package uz.gita.vogayerlib

import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenModelImpl(val value: KClass<out Any>)
