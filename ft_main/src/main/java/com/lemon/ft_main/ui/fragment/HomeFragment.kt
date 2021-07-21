package com.lemon.ft_main.ui.fragment

import com.lemon.ft_main.BR
import com.lemon.ft_main.R
import com.lemon.ft_main.databinding.MainFragmentHomeBindingImpl
import com.lemon.ft_main.viewmodel.HomeViewModel
import com.lemon.lib_base.base.BaseFragment
import com.lemon.lib_base.utils.RxThreadHelper
import com.lxj.xpopup.core.BasePopupView
import com.youth.banner.transformer.AlphaPageTransformer
import io.reactivex.disposables.Disposable
import org.koin.core.qualifier.named

class HomeFragment:BaseFragment<MainFragmentHomeBindingImpl,HomeViewModel> (){




    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(): Int {
       return R.layout.main_fragment_home
    }


    override fun initData() {
        super.initData()
        viewModel.tabSelectedPosition.set(0)
//        initBanner()
//        initSearchBar()
//        initArticleAdapter()
//        initProjectAdapter()
//        // 加载保存时间为AppConstants.CacheKey.CACHE_SAVE_TIME_SECONDS的缓存 过期则刷新加载
//        loadData()
    }



//
//    /**
//     * 刷新搜索记录
//     */
//    private fun setSuggestAdapterData() {
//        viewModel.addSubscribe(viewModel.model.getSearchHistoryByUid()
//            .compose(RxThreadHelper.rxFlowSchedulerHelper())
//            .subscribe {
//                suggestAdapter.suggestions = it.map { x -> x.history }
//                if (suggestAdapter.suggestions.isEmpty()) binding.searchBar.hideSuggestionsList()
//            })
//    }
//
//    private fun initSearchBar() {
//        if (!this::mHomeDrawerPop.isInitialized) {
//            mHomeDrawerPop = HomeDrawerPop(this)
//        }
//        if (!this::suggestAdapter.isInitialized) {
//            suggestAdapter = SearchSuggestAdapter(layoutInflater)
//            suggestAdapter.setListener(viewModel.onSearchItemClick)
//            setSuggestAdapterData()
//        }
//        binding.searchBar.apply {
//            setMaxSuggestionCount(5)
//            setCustomSuggestionAdapter(suggestAdapter)
//        }
//    }
//
//    private fun initBanner() {
//        bannerSkeleton = Skeleton.bind(binding.banner)
//            .load(R.layout.main_banner_skeleton)
//            .show()
//        binding.banner.apply {
//            addBannerLifecycleObserver(this@HomeFragment)
//            //            setBannerGalleryMZ(20)
//            setBannerGalleryEffect(18, 10)
//            addPageTransformer(AlphaPageTransformer(0.6f))
//        }
//    }
}