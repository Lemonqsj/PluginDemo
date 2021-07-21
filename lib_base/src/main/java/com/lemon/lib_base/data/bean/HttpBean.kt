package com.lemon.lib_base.data.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.lemon.lib_base.BR

data class UserBean(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("chapterTops")
    val chapterTops: List<Any>,
    @SerializedName("coinCount")
    val coinCount: Int,
    @SerializedName("collectIds")
    val collectIds: List<Any>,
    @SerializedName("email")
    val email: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("publicName")
    val publicName: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("username")
    val username: String

)

data class ProjectBean(
    @SerializedName("curPage")
    val curPage: Int,
    @SerializedName("datas")
    val datas: List<Data>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("over")
    val over: Boolean,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
): Serializable {
    data class Data(
        @SerializedName("apkLink")
        val apkLink: String,
        @SerializedName("audit")
        val audit: Int,
        @SerializedName("author")
        val author: String,
        @SerializedName("canEdit")
        val canEdit: Boolean,
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("chapterName")
        val chapterName: String,
//        @SerializedName("collect")
//        var collect: Boolean,
        @SerializedName("courseId")
        val courseId: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("descMd")
        val descMd: String,
        @SerializedName("envelopePic")
        val envelopePic: String,
        @SerializedName("fresh")
        val fresh: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("niceDate")
        val niceDate: String,
        @SerializedName("niceShareDate")
        val niceShareDate: String,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("projectLink")
        val projectLink: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("realSuperChapterId")
        val realSuperChapterId: Int,
        @SerializedName("selfVisible")
        val selfVisible: Int,
        @SerializedName("shareDate")
        val shareDate: Long,
        @SerializedName("shareUser")
        val shareUser: String,
        @SerializedName("superChapterId")
        val superChapterId: Int,
        @SerializedName("superChapterName")
        val superChapterName: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("visible")
        val visible: Int,
        @SerializedName("zan")
        val zan: Int
    ) : BaseObservable(), Serializable {
        @Bindable
        @SerializedName("collect")
        var collect: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.collect)
            }

        data class Tag(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        ): Serializable
    }
}

data class HomeBannerBean(
    @SerializedName("desc")
    val desc: String = "",
    @SerializedName("id")
    val id: Int,
    @SerializedName("imagePath")
    val imagePath: String = "",
    @SerializedName("isVisible")
    val isVisible: Int,
    @SerializedName("order")
    val order: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: Int,
    @SerializedName("url")
    val url: String = ""
):Serializable

data class SearchDataBean(
    @SerializedName("curPage")
    val curPage: Int,
    @SerializedName("datas")
    val datas: List<Data>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("over")
    val over: Boolean,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
) {
    data class Data(
        @SerializedName("apkLink")
        val apkLink: String,
        @SerializedName("audit")
        val audit: Int,
        @SerializedName("author")
        val author: String,
        @SerializedName("canEdit")
        val canEdit: Boolean,
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("chapterName")
        val chapterName: String,
        @SerializedName("courseId")
        val courseId: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("descMd")
        val descMd: String,
        @SerializedName("envelopePic")
        val envelopePic: String,
        @SerializedName("fresh")
        val fresh: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("niceDate")
        val niceDate: String,
        @SerializedName("niceShareDate")
        val niceShareDate: String,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("projectLink")
        val projectLink: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("realSuperChapterId")
        val realSuperChapterId: Int,
        @SerializedName("selfVisible")
        val selfVisible: Int,
        @SerializedName("shareDate")
        val shareDate: Long,
        @SerializedName("shareUser")
        val shareUser: String,
        @SerializedName("superChapterId")
        val superChapterId: Int,
        @SerializedName("superChapterName")
        val superChapterName: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("visible")
        val visible: Int,
        @SerializedName("zan")
        val zan: Int
    ) : BaseObservable() {
        @Bindable
        @SerializedName("collect")
        var collect: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.collect)
            }

        data class Tag(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }
}

data class SearchHotKeyBean(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("visible")
    val visible: Int
):Serializable

data class HomeArticleBean(
    @SerializedName("curPage")
    val curPage: Int,
    @SerializedName("datas")
    var datas: ArrayList<Data>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("over")
    val over: Boolean,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
) :Serializable{
    data class Data(
        @SerializedName("apkLink")
        val apkLink: String,
        @SerializedName("audit")
        val audit: Int,
        @SerializedName("author")
        val author: String,
        @SerializedName("canEdit")
        val canEdit: Boolean,
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("chapterName")
        val chapterName: String,
//        @SerializedName("collect")
//        var collect: Boolean,
        @SerializedName("courseId")
        val courseId: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("descMd")
        val descMd: String,
        @SerializedName("envelopePic")
        val envelopePic: String,
        @SerializedName("fresh")
        val fresh: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("niceDate")
        val niceDate: String,
        @SerializedName("niceShareDate")
        val niceShareDate: String,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("projectLink")
        val projectLink: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("realSuperChapterId")
        val realSuperChapterId: Int,
        @SerializedName("selfVisible")
        val selfVisible: Int,
        @SerializedName("shareDate")
        val shareDate: Long,
        @SerializedName("shareUser")
        val shareUser: String,
        @SerializedName("superChapterId")
        val superChapterId: Int,
        @SerializedName("superChapterName")
        val superChapterName: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("visible")
        val visible: Int,
        @SerializedName("zan")
        val zan: Int
    ) : BaseObservable(),Serializable {
        @Bindable
        var collect: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.collect)
            }

        @Bindable
        var topFlag: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.topFlag)
            }

        data class Tag(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        ):Serializable
    }
}