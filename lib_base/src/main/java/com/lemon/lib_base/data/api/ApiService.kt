package com.lemon.lib_base.data.api


import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.bean.*
import io.reactivex.Observable
import retrofit2.http.*

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

    /**
     * 首页轮播图
     */
    @GET("banner/json")
    fun getBannerData(): Observable<BaseBean<List<HomeBannerBean>>>

    /**
     * 首页热门项目列表
     */
    @GET("article/listproject/{page}/json")
    fun getHomeProject(@Path("page") page: String): Observable<BaseBean<ProjectBean>>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun searchByKeyword(
        @Path("page") page: String,
        @Field("k") keyword: String
    ): Observable<BaseBean<SearchDataBean>>


    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    fun getSearchHotKey(): Observable<BaseBean<List<SearchHotKeyBean>>>

    /**
     * 首页热门博文列表
     */
    @GET("article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: String): Observable<BaseBean<HomeArticleBean>>

    /**
     * 获取首页置顶文章
     */
    @GET("article/top/json")
    fun getHomeTopArticle(): Observable<BaseBean<List<HomeArticleBean.Data>>>

}