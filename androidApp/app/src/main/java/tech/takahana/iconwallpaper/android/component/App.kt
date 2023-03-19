package tech.takahana.iconwallpaper.android.component

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import tech.takahana.iconwallpaper.android.BuildConfig
import tech.takahana.iconwallpaper.android.core.utils.MediaStoreManager

@HiltAndroidApp
class App : Application(), DefaultLifecycleObserver {

    private val mediaStoreManager: MediaStoreManager by lazy {
        MediaStoreManager(applicationContext)
    }

    override fun onCreate() {
        super<Application>.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        mediaStoreManager.delete(MediaStoreManager.Group.TmpForSetWallpaperByOtherApp)
    }
}
