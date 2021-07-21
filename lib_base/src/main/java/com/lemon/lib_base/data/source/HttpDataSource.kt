package com.lemon.lib_base.data.source

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.bean.*
import io.reactivex.Observable

interface HttpDataSource {
    fun userLogin(account:String,pwd:String):Observable<BaseBean<UserBean>>
    fun register(userName:String,pwd:String,repwd:String):Observable<BaseBean<Any>>


    fun getBannerData(): Observable<BaseBean<List<HomeBannerBean>>>

    fun searchByKeyword(page: String = "0", keyword: String): Observable<BaseBean<SearchDataBean>>

    fun getSearchHotKey(): Observable<BaseBean<List<SearchHotKeyBean>>>
    fun getHomeProject(page: String = "0"): Observable<BaseBean<ProjectBean>>

    fun getHomeArticle(page: String = "0"): Observable<BaseBean<HomeArticleBean>>

    fun getHomeTopArticle(): Observable<BaseBean<List<HomeArticleBean.Data>>>
}