package com.lemon.ft_main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.lemon.ft_main.BR
import com.lemon.ft_main.R
import com.lemon.ft_main.adapter.HomeArticleAdapter
import com.lemon.ft_main.adapter.HomeProjectAdapter
import com.lemon.ft_main.adapter.MyBannerAdapter
import com.lemon.ft_main.databinding.MainFragmentHomeBinding
import com.lemon.ft_main.viewmodel.HomeViewModel
import com.lemon.lib_base.base.BaseFragment
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.data.bean.HomeArticleBean
import com.lemon.lib_base.data.bean.HomeBannerBean
import com.lemon.lib_base.data.bean.SearchHotKeyBean
import com.lemon.lib_base.utils.RxThreadHelper
import com.lxj.xpopup.core.BasePopupView
import com.youth.banner.transformer.AlphaPageTransformer
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier.named
@Route(path = AppConstants.Router.Main.F_HOME)
class HomeFragment:BaseFragment<MainFragmentHomeBinding,HomeViewModel> (){

    private lateinit var bannerSkeleton: SkeletonScreen
    private lateinit var bannerAdapter: MyBannerAdapter

    lateinit var mArticleAdapter: HomeArticleAdapter
    lateinit var mProjectAdapter: HomeProjectAdapter

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(): Int {
       return R.layout.main_fragment_home
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingStatePage()
    }


    override fun initData() {
        super.initData()
        viewModel.tabSelectedPosition.set(0)
        initBanner()
        initArticleAdapter()
        initProjectAdapter()
//        initSearchBar()
//        initArticleAdapter()
//        initProjectAdapter()
//        // ?????????????????????AppConstants.CacheKey.CACHE_SAVE_TIME_SECONDS????????? ?????????????????????
        loadData()
    }

    private fun loadData() {

        val bannerObservable = Observable.create<List<HomeBannerBean?>> {
            it.onNext(viewModel.getCacheData(AppConstants.CacheKey.CACHE_HOME_BANNER))}
        val articleObservable = Observable.create<List<HomeArticleBean.Data?>> {
            it.onNext(viewModel.getCacheData(AppConstants.CacheKey.CACHE_HOME_ARTICLE))
        }.subscribeOn(Schedulers.io())
        val keywordObservable = Observable.create<List<SearchHotKeyBean>> {
            it.onNext(viewModel.getCacheData(AppConstants.CacheKey.CACHE_HOME_KEYWORD))
        }.subscribeOn(Schedulers.io())


        viewModel.addSubscribe(Observable.merge(
            bannerObservable,
            articleObservable,
            keywordObservable
        )
            .compose(RxThreadHelper.rxSchedulerHelper())
            .subscribe({
                showSuccessStatePage()
                LogUtils.d("--------------"+it)
                if (!it.isNullOrEmpty()) {
                    when (it[0]) {
                        is HomeBannerBean? -> {
                            bannerSkeleton.hide()
                            bannerAdapter = MyBannerAdapter(it as List<HomeBannerBean?>, this)
                            binding.banner.adapter = bannerAdapter
                        }
                        is HomeArticleBean.Data? -> {
                            binding.ryArticle.hideShimmerAdapter()
                            mArticleAdapter.setDiffNewData(it as MutableList<HomeArticleBean.Data>?)
                        }
                        is SearchHotKeyBean -> {
//                            setTimerHotKey(it as List<SearchHotKeyBean>)
                        }
                    }
                } else {
                    LogUtils.d("--------autoRefresh------"+binding.smartCommon.autoRefresh())


                }
            }) {
                LogUtils.d("--------err------"+it)
                showSuccessStatePage()
                it.printStackTrace()
                binding.smartCommon.autoRefresh()
            })


    }

    override fun reload() {
        super.reload()
        binding.smartCommon.autoRefresh()
    }

    override fun initViewObservable() {

        // ???????????????????????????
        viewModel.uc.bannerCompleteEvent.observe(this, {
            bannerSkeleton.hide()
            if (!this::bannerAdapter.isInitialized) {
                // ???????????????
//                viewModel.model.saveCacheListData(it)
                bannerAdapter = MyBannerAdapter(it, this)
                binding.banner.adapter = bannerAdapter
            } else {
                bannerAdapter.setData(binding.banner, it)
            }
        })


        // ??????
        viewModel.uc.moveTopEvent.observe(this, { tabPosition ->
            when (tabPosition) {
                0 -> binding.ryArticle.smoothScrollToPosition(0)
                1 -> binding.ryProject.smoothScrollToPosition(0)
            }
        })

        // ????????????????????????
        viewModel.uc.loadArticleCompleteEvent.observe(this, { data ->
            if (!data?.datas.isNullOrEmpty()) {
//                viewModel.model.saveCacheListData(data!!.datas)
            }
            handleRecyclerviewData(
                data == null,
                data?.datas as MutableList<*>?,
                mArticleAdapter,
                binding.ryArticle,
                binding.smartCommon,
                viewModel.currentArticlePage,
                data?.over
            )
        })
        // ????????????????????????
        viewModel.uc.loadProjectCompleteEvent.observe(this, { data ->
            handleRecyclerviewData(
                data == null,
                data?.datas as MutableList<*>?,
                mProjectAdapter,
                binding.ryProject,
                binding.smartCommon,
                viewModel.currentProjectPage,
                data?.over
            )
        })
    }

    private fun initArticleAdapter() {
        mArticleAdapter = HomeArticleAdapter(this)
        mArticleAdapter.setDiffCallback(mArticleAdapter.diffConfig)
        binding.ryArticle.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mArticleAdapter
            setDemoLayoutReference(R.layout.main_article_item_skeleton)
            setDemoLayoutManager(ShimmerRecyclerView.LayoutMangerType.LINEAR_VERTICAL)
            showShimmerAdapter()
        }
    }

    private fun initProjectAdapter() {
        mProjectAdapter = HomeProjectAdapter(this)
        mProjectAdapter.setDiffCallback(mProjectAdapter.diffConfig)
        binding.ryProject.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mProjectAdapter
            setDemoLayoutReference(R.layout.main_item_project_skeleton)
            setDemoLayoutManager(ShimmerRecyclerView.LayoutMangerType.LINEAR_VERTICAL)
            showShimmerAdapter()
        }
    }

    private fun initBanner() {
        bannerSkeleton = Skeleton.bind(binding.banner)
            .load(R.layout.main_banner_skeleton)
            .show()
        binding.banner.apply {
            addBannerLifecycleObserver(this@HomeFragment)
            //            setBannerGalleryMZ(20)
            setBannerGalleryEffect(18, 10)
            addPageTransformer(AlphaPageTransformer(0.6f))
        }
    }
}