package tech.takahana.iconwallpaper.shared.domain.domainobject

/**
 * @param drawNum 一つの行に並べる要素の数
 */
enum class PatternType(val drawNum: Int) {
    SMALL(1),
    MEDIUM(3),
    LARGE(5)
}
