package com.lemon.lib_base.data.source

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.bean.UserBean
import io.reactivex.Observable

interface HttpDataSource {
    fun userLogin(account:String,pwd:String):Observable<BaseBean<UserBean>>
    fun register(userName:String,pwd:String,repwd:String):Observable<BaseBean<Any>>
}