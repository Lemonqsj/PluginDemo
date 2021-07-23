package com.lemon.lib_base.binding.viewadapter.smartrefresh

import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.LogUtils
import com.lemon.lib_base.binding.command.BindingCommand
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout

object ViewAdapter {
    //下拉刷新命令
    @JvmStatic
    @BindingAdapter("onRefreshCommand")
    fun onRefreshCommand(
        smartRefreshLayout: SmartRefreshLayout,
        onRefreshCommand: BindingCommand<*>?
    ) {
        LogUtils.d("------onRefreshCommand----"+(smartRefreshLayout==null)+"----------"+(onRefreshCommand==null))
        smartRefreshLayout.setOnRefreshListener { refreshLayout: RefreshLayout? -> onRefreshCommand?.execute() }
    }

    @JvmStatic
    @BindingAdapter("onLoadMoreCommand")
    fun onLoadMoreCommand(
        smartRefreshLayout: SmartRefreshLayout,
        onLoadCommand: BindingCommand<*>?
    ) {
        LogUtils.d("------onLoadMoreCommand----"+(smartRefreshLayout==null)+"----------"+(onLoadCommand==null))
        smartRefreshLayout.setOnLoadMoreListener { refreshLayout: RefreshLayout? -> onLoadCommand?.execute() }
    }
}