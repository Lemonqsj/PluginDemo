package com.lemon.lib_base.data

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.base.BaseModel
import com.lemon.lib_base.data.bean.UserBean
import com.lemon.lib_base.data.source.HttpDataSource
import com.lemon.lib_base.data.source.LocalDataSource
import io.reactivex.Observable

class DataRespository constructor(
    private val mLocalDataSource: LocalDataSource,
    private val mHttpDataSource: HttpDataSource
) : BaseModel(), LocalDataSource, HttpDataSource {
    override fun getLoginName(): String? {
        return mLocalDataSource.getLoginName()
    }

    override fun userLogin(account: String, pwd: String): Observable<BaseBean<UserBean>> {
        return mHttpDataSource.userLogin(account, pwd)
    }

    override fun register(userName: String, pwd: String, repwd: String): Observable<BaseBean<Any>> {
        return mHttpDataSource.register(userName, pwd, repwd)
    }
}