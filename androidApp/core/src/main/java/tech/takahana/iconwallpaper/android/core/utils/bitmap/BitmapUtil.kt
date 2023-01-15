package tech.takahana.iconwallpaper.android.core.utils.bitmap

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.rotate90(): Bitmap {
    val w = this.width
    val h = this.height
    val m = Matrix()
    m.setRotate(90.0F)
    return Bitmap.createBitmap(this, 0, 0, w, h, m, false)
}
