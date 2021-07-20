package com.lemon.lib_base.mvvm.ui

import android.os.Bundle
import com.lemon.lib_base.BR
import com.lemon.lib_base.R
import com.lemon.lib_base.base.BaseActivity
import com.lemon.lib_base.databinding.CommonContainerBinding
import com.lemon.lib_base.mvvm.viewmodel.CommonViewModel
import com.lemon.lib_base.route.RouteCenter
import me.yokeyword.fragmentation.SupportFragment

class ContainerFmActivity:BaseActivity<CommonContainerBinding,CommonViewModel>() {

    companion object {
        const val FRAGMENT = "fragment"
        const val BUNDLE = "bundle"
    }

    override fun initViewModelId(): Int {
       return BR.viewmodel
    }

    override fun initLayoutContentView(): Int {
       return R.layout.common_container
    }

    override fun initParam() {

    }

    override fun initData() {
        val fragmentPath: String? = intent.getStringExtra(FRAGMENT)
        if (fragmentPath.isNullOrBlank()) {
            return
        }
        val args: Bundle? = intent.getBundleExtra(BUNDLE)
        val fragment: SupportFragment = RouteCenter.navigate(fragmentPath,args) as SupportFragment
        if (findFragment(fragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, fragment)
        }
    }

    override fun initViewObservable() {
    }
}