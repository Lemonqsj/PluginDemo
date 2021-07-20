package com.lemon.lib_base.data.api


import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.bean.UserBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @POST("user/register")
    @FormUrlEncoded
    fun register(
        @Field("userName") userName: String,
        @Field("password") password: String,
        @Field("rePassword") rePassword: String
    ): Observable<BaseBean<Any>>


    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun pwdLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseBean<UserBean>>
}