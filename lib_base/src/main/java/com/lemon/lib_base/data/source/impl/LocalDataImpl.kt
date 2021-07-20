package com.lemon.lib_base.data.source.impl

import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.data.source.LocalDataSource
import com.lemon.lib_base.utils.SpHelper

class LocalDataImpl:LocalDataSource {
    override fun getLoginName(): String? {
        return SpHelper.decodeString(AppConstants.SpKey.LOGIN_NAME)
    }
}