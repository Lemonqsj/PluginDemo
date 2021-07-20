package com.lemon.ft_main.viewmodel

import com.lemon.lib_base.base.BaseViewModel
import com.lemon.lib_base.base.MyApplication
import com.lemon.lib_base.binding.command.BindingCommand
import com.lemon.lib_base.binding.command.BindingConsumer
import com.lemon.lib_base.data.DataRespository

class MainViewModel(application: MyApplication, model: DataRespository) :
    BaseViewModel<DataRespository>(application, model) {

    val onTabSelectedListener: BindingCommand<Int> = BindingCommand(BindingConsumer {
//        uc.tabChangeLiveEvent.postValue(it)
    })

    val onPageSelectedListener: BindingCommand<Int> = BindingCommand(BindingConsumer {
//        uc.pageChangeLiveEvent.postValue(it)
    })



}