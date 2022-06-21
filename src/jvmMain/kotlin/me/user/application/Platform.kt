package me.user.application

actual class Platform actual constructor() {
    actual val platform: String
        get() = "jvm"

}