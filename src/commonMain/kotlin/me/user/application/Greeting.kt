package me.user.application


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
