package com.lemon.lib_base.data.source.impl

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.data.api.ApiService
import com.lemon.lib_base.data.bean.*
import com.lemon.lib_base.data.source.HttpDataSource
import io.reactivex.Observable


class HttpDataImpl(private val apiService:ApiService):HttpDataSource {
    override fun userLogin(account: String, pwd: String): Observable<BaseBean<UserBean>> {
        return apiService.pwdLogin(account,pwd)
    }

    override fun register(userName: String, pwd: String, repwd: String): Observable<BaseBean<Any>> {
        return apiService.register(userName,pwd,repwd)
    }

    override fun getBannerData(): Observable<BaseBean<List<HomeBannerBean>>> {
        return apiService.getBannerData()
    }

    override fun searchByKeyword(
        page: String,
        keyword: String
    ): Observable<BaseBean<SearchDataBean>> {
        return apiService.searchByKeyword(page,keyword)
    }

    override fun getSearchHotKey(): Observable<BaseBean<List<SearchHotKeyBean>>> {
      return apiService.getSearchHotKey()
    }

    override fun getHomeProject(page: String): Observable<BaseBean<ProjectBean>> {
       return apiService.getHomeProject(page)
    }

    override fun getHomeArticle(page: String): Observable<BaseBean<HomeArticleBean>> {
        return apiService.getHomeArticle(page)
    }

    override fun getHomeTopArticle(): Observable<BaseBean<List<HomeArticleBean.Data>>> {
       return apiService.getHomeTopArticle()
    }
}