package tech.takahana.iconwallpaper.shared.domain.domainobject

abstract class ColorType(open val hex: Long) {
    object Red : ColorType(0xFFFF0000)
    object Blue : ColorType(0xFF0000FF)
    object Green : ColorType(0xFF00FF00)
    object Purple : ColorType(0xFFA260BF)
    object Yellow : ColorType(0xFFFFFF00)
    object Beige : ColorType(0xFFF4BE9B)
    object Cyan : ColorType(0xFF00FFFF)

    data class Other(override val hex: Long) : ColorType(hex = hex)
}
