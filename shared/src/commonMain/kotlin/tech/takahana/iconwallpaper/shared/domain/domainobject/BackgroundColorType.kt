package tech.takahana.iconwallpaper.shared.domain.domainobject

sealed class BackgroundColor(open val rgb: Long) {
    object RED : BackgroundColor(0xFFFF0000)
    object BLUE : BackgroundColor(0xFF0000FF)
    object GREEN : BackgroundColor(0xFF00FF00)
    object PURPLE : BackgroundColor(0xFFA260BF)
    object YELLOW : BackgroundColor(0xFFFFFF00)
    object BEIGE : BackgroundColor(0xFFF4BE9B)
    object CYAN : BackgroundColor(0xFF00FFFF)

    data class Other(override val rgb: Long) : BackgroundColor(rgb = rgb)
}
