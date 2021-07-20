package com.lemon.lib_base.route

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils

object RouteCenter {

    fun navigate(path: String, bundle: Bundle? = null): Any? {
        val build = ARouter.getInstance().build(path)
        LogUtils.e("-------------"+path)
        return if (bundle == null) build.navigation() else build.with(bundle).navigation()
    }
}