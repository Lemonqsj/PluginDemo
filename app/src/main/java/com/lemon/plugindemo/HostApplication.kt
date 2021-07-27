package com.lemon.plugindemo

import android.annotation.SuppressLint
import android.util.Log
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.RePluginApplication
import com.qihoo360.replugin.RePluginConfig
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class HostApplication :RePluginApplication() {

    override fun onCreate() {
        super.onCreate()
//
//        preloadPlugin()
//        //注册接口中实现
//        //注册接口中实现
//        RePlugin.registerGlobalBinder(HostPluginConfig.KEY_INTERFACE, IHostServiceImpl())
    }


    /**
     * 预加载关键插件在IO线程中
     */
    @SuppressLint("CheckResult")
    private fun preloadPlugin() {
//        Observable.fromCallable {
//            RePlugin.preload(LoadingPluginConfig.PLUGIN_NAME) && RePlugin.preload(
//                HomePluginConfig.PLUGIN_NAME
//            ) && RePlugin.preload("ft_mine") && RePlugin.preload(
//                "ft_discovery"
//            ) && RePlugin.preload("ft_friend")
//        }.subscribeOn(Schedulers.io()).subscribe({
////            Log.d(
////                com.imooc.imooc_voice.application.ImoocVoiceApplication.TAG,
////                "plugin preload success."
////            )
//        }
//        ) {
////            Log.d(
////                com.imooc.imooc_voice.application.ImoocVoiceApplication.TAG,
////                "plugin preload has problem."
////            )
//        }
    }

    override fun createConfig(): RePluginConfig? {
        val config = super.createConfig()
        //使插件可以使用主工程中的类
        config.isUseHostClassIfNotFound = true
        return config
    }


}