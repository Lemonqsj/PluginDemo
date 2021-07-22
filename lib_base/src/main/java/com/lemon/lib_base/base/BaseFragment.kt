package com.lemon.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.Convertor
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lemon.lib_base.R
import com.lemon.lib_base.callback.ErrorCallback
import com.lemon.lib_base.callback.LoadErrorCallback
import com.lemon.lib_base.callback.LoadingCallback
import com.lxj.xpopup.core.BasePopupView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import org.koin.android.ext.android.get
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel<*>> : BaseRxFragment(),
    IBaseView {

    protected var ryCommon: RecyclerView? = null
    lateinit var binding: V
    lateinit var viewModel: VM
    private var viewModelId = 0
    private val dialog: BasePopupView? = null

    private lateinit var rootView: View
    protected var rootBinding: ViewDataBinding? = null

    lateinit var loadService: LoadService<BaseBean<*>?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val loadSir = LoadSir.Builder()
            .addCallback(LoadErrorCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .build()



        binding = DataBindingUtil.inflate(inflater, initContentView(), container, false)
        loadService = loadSir.register(binding.root,
            Callback.OnReloadListener { reload() },
            Convertor<BaseBean<*>?> { t ->
                if (t == null || t.errorCode != 0) {
                    ErrorCallback::class.java
                } else {
                    SuccessCallback::class.java
                }
            }) as LoadService<BaseBean<*>?>
        return loadService.loadLayout


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViewDataBinding()
    }


    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        if (enableLazyLoad()) {
            initData()

            initViewObservable()
        }

    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)

        if (!enableLazyLoad()) {
            initData()
            initViewObservable()
        }
    }

    /**
     * @return 是否需要标题栏
     */
    protected open fun useBaseLayout(): Boolean {
        return true
    }

    private fun initViewDataBinding() {
        viewModelId = initVariableId()
        viewModel = initViewModel()
        rootBinding?.setVariable(viewModelId, viewModel)
        rootBinding?.lifecycleOwner = this

        binding.setVariable(viewModelId, viewModel)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        viewModel.injectLifecycleProvider(this)

        viewModel.loadService=loadService


    }

    private fun initViewModel(): VM {
        val type = javaClass.genericSuperclass
        val modelClass: Class<VM> =
            (type as ParameterizedType).actualTypeArguments[1] as Class<VM>
//        LogUtils.d("------initViewModel---------"+modelClass.name)
        return ViewModelProvider(this, get() as AppViewModelFactory).get(modelClass)
    }

    abstract fun initVariableId(): Int

    private fun reload() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    override fun initParam() {
    }

    override fun initData() {
    }

    override fun initViewObservable() {
    }

    abstract fun initContentView(): Int


    protected open fun enableLazyLoad(): Boolean {
        return true
    }

    /**
     * 统一处理列表数据
     */
    fun <T> handleRecyclerviewData(
        nullFlag: Boolean,
        data: MutableList<*>?,
        mAdapter: BaseQuickAdapter<T, *>,
        ryCommon: ShimmerRecyclerView,
        smartCommon: SmartRefreshLayout,
        currentPage: Int,
        over: Boolean?,
        defaultPage: Int = 0
    ) {
        this.ryCommon = ryCommon
        if (nullFlag) {
            if (currentPage == defaultPage - 1)
                showErrorStatePage()
            smartCommon.finishRefresh(false)
            smartCommon.finishLoadMore(false)
            return
        }
        showSuccessStatePage()
        if (currentPage == defaultPage) {
            ryCommon.hideShimmerAdapter()
            if (!mAdapter.hasEmptyView()) {
                val emptyView = View.inflate(context, R.layout.common_empty_layout, null)
                emptyView.findViewById<ViewGroup>(R.id.ll_empty).setOnClickListener {
                    smartCommon.autoRefresh()
                }
                mAdapter.setEmptyView(emptyView)
            }
            mAdapter.setDiffNewData(data as MutableList<T>?)
            ryCommon.smoothScrollToPosition(0)
            if (over!!) smartCommon.finishRefreshWithNoMoreData()
            else smartCommon.finishRefresh(true)
            return
        }
        if (over!!) smartCommon.finishLoadMoreWithNoMoreData()
        else smartCommon.finishLoadMore(true)
        mAdapter.addData(data as MutableList<T>)
    }


    fun showErrorStatePage() {
        loadService.showCallback(ErrorCallback::class.java)
    }

    fun showLoadingStatePage() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    fun showSuccessStatePage() {
        loadService.showCallback(SuccessCallback::class.java)
    }

}