package com.lemon.lib_base.base

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.lemon.lib_base.mvvm.ui.ContainerFmActivity
import org.koin.android.ext.android.get
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> : BaseRxActivity(),
    IBaseView {

    protected lateinit var binding: V
    lateinit var viewModel: VM
    private var viewModelId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 接受页面传递的参数
        initParam();


        initViewDataBinding(savedInstanceState)

        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
    }

    fun initViewDataBinding(savedInstanceState: Bundle?) {

        binding = DataBindingUtil.setContentView(this, initLayoutContentView())

        //初始化viewmodel 通过反射的方式自定义，不过需要注意viewmodel的创建方式需要通过属性委托的方式进行创建，不然每次无法恢复activity的状态
        viewModel = initViewModel()
        viewModelId = initViewModelId()
        //databinding绑定viewmodel，也就是给databinding设置数据，databinding设置数据有两种方式，一种是binding.setuser(),第二种是这样直接传入的方式
        binding.setVariable(viewModelId, viewModel)

        binding.lifecycleOwner = this

        //viewmodel监听宿主的声明周期
        lifecycle.addObserver(viewModel)

        // viewmodel中注入声明周期
        viewModel.injectLifecycleProvider(this)

    }

    private fun initViewModel(): VM {
        val type = javaClass.genericSuperclass
        val modelClass = (type as ParameterizedType).actualTypeArguments[1] as Class<VM>

        return ViewModelProvider(this, get<AppViewModelFactory>()).get(modelClass)
    }

    abstract fun initViewModelId(): Int

    abstract fun initLayoutContentView(): Int


    fun startContainerActivity(routePath: String?, bundle: Bundle? = null) {
        val intent = Intent(this, ContainerFmActivity::class.java)
        intent.putExtra(ContainerFmActivity.FRAGMENT, routePath)
        if (bundle != null)
            intent.putExtra(ContainerFmActivity.BUNDLE, bundle)

        startActivity(intent)
    }

}