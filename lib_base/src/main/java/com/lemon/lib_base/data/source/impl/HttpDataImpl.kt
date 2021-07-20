package com.lemon.lib_base.data.source.impl

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.api.ApiService
import com.lemon.lib_base.data.bean.UserBean
import com.lemon.lib_base.data.source.HttpDataSource
import io.reactivex.Observable


class HttpDataImpl(private val apiService:ApiService):HttpDataSource {
    override fun userLogin(account: String, pwd: String): Observable<BaseBean<UserBean>> {
        return apiService.pwdLogin(account,pwd)
    }

    override fun register(userName: String, pwd: String, repwd: String): Observable<BaseBean<Any>> {
        return apiService.register(userName,pwd,repwd)
    }
}