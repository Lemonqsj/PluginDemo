package com.lemon.lib_base.data.source.impl

import com.blankj.utilcode.util.CacheDiskUtils
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.data.bean.HomeArticleCache
import com.lemon.lib_base.data.bean.HomeBannerCache
import com.lemon.lib_base.data.bean.HomeProjectContentCache
import com.lemon.lib_base.data.bean.HomeSearchKeywordCache
import com.lemon.lib_base.data.source.LocalDataSource
import com.lemon.lib_base.utils.SpHelper
import java.io.Serializable

class LocalDataImpl:LocalDataSource {
    override fun getLoginName(): String? {
        return SpHelper.decodeString(AppConstants.SpKey.LOGIN_NAME)
    }

    override fun <T : Serializable> getCacheListData(key: String): List<T>? {
        return when (val serializable = CacheDiskUtils.getInstance().getSerializable(key)) {
            is HomeBannerCache? -> {
                serializable?.homeBannerBeans as List<T>?
            }
            is HomeArticleCache? -> {
                serializable?.homeArticleBeans as List<T>?
            }
            is HomeSearchKeywordCache? -> {
                serializable?.searchHotKeyBean as List<T>?
            }
//            is HomeSquareCache? -> {
//                serializable?.squareCache as List<T>?
//            }
//            is HomeProjectSortCache? -> {
//                serializable?.sortCache as List<T>?
//            }
            is HomeProjectContentCache? -> {
                serializable?.contentCache as List<T>?
            }
            else -> emptyList()
        }
    }
}