package tech.takahana.iconwallpaper

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
