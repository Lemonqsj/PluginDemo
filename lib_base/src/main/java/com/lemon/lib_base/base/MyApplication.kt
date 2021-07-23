package com.lemon.lib_base.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.lemon.lib_base.BuildConfig
import com.lemon.lib_base.R
import com.lemon.lib_base.di.allModule
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import me.yokeyword.fragmentation.Fragmentation
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setApplication(this)
        ARouter.init(this)

        // 初始化Fragmentation
        Fragmentation.builder()
            .stackViewMode(Fragmentation.NONE)
            .debug(BuildConfig.DEBUG)
            .install()


        MMKV.initialize(this)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(allModule)
        }



    }

    private fun setApplication(application: Application) {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // TODO: 2021/5/24  
            }

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
                // TODO: 2021/5/24
            }

        })
    }


    override fun onLowMemory() {
        super.onLowMemory()

        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        init {
            ClassicsFooter.REFRESH_FOOTER_FINISH = ""
            SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
                layout.apply {
                    setEnableOverScrollDrag(true)
                    setEnableScrollContentWhenLoaded(false)
                    setEnableAutoLoadMore(true)
                    setEnableOverScrollBounce(true)
                    setFooterHeight(60f)
                }
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.apply {
                    setPrimaryColorsId(R.color.md_theme_red, R.color.white)
                }
                MaterialHeader(context).setColorSchemeColors(
                    ContextCompat.getColor(
                        context,
                        R.color.md_theme_red
                    )
                )
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(context).setFinishDuration(0)
            }
        }
    }

}