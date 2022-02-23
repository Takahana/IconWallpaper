package tech.takahana.iconwallpaper.shared.domain.domainobject

import kotlin.jvm.JvmInline

@JvmInline
value class AssetId(val value: String) {

    init {
        require(isValid(value))
    }

    companion object {

        fun isValid(value: String): Boolean {
            return value.isNotEmpty()
        }

        fun requireGet(value: String) = AssetId(value)

        fun getOrNull(value: String) = try {
            AssetId(value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
