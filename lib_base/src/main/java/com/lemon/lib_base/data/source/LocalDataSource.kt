package com.lemon.lib_base.data.source

import java.io.Serializable

interface LocalDataSource {
    fun getLoginName():String?
    fun <T : Serializable> getCacheListData(key:String): List<T>?
}