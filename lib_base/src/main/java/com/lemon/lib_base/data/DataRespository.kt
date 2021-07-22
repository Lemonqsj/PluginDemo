package com.lemon.lib_base.data

import com.lemon.lib_base.base.BaseBean
import com.lemon.lib_base.base.BaseModel
import com.lemon.lib_base.data.bean.*
import com.lemon.lib_base.data.source.HttpDataSource
import com.lemon.lib_base.data.source.LocalDataSource
import io.reactivex.Observable
import java.io.Serializable

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

    override fun getBannerData(): Observable<BaseBean<List<HomeBannerBean>>> {
       return mHttpDataSource.getBannerData()
    }

    override fun searchByKeyword(
        page: String,
        keyword: String
    ): Observable<BaseBean<SearchDataBean>> {
        return mHttpDataSource.searchByKeyword(page, keyword)
    }

    override fun getSearchHotKey(): Observable<BaseBean<List<SearchHotKeyBean>>> {
        return mHttpDataSource.getSearchHotKey()
    }

    override fun getHomeProject(page: String): Observable<BaseBean<ProjectBean>> {
        return mHttpDataSource.getHomeProject(page)
    }

    override fun getHomeArticle(page: String): Observable<BaseBean<HomeArticleBean>> {
        return mHttpDataSource.getHomeArticle(page)
    }

    override fun getHomeTopArticle(): Observable<BaseBean<List<HomeArticleBean.Data>>> {
       return mHttpDataSource.getHomeTopArticle()
    }


    override fun <T : Serializable> getCacheListData(key:String): List<T>? {
        return mLocalDataSource.getCacheListData(key)
    }

}