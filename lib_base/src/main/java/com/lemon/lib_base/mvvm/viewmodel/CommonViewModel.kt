package com.lemon.lib_base.mvvm.viewmodel

import com.lemon.lib_base.base.BaseViewModel
import com.lemon.lib_base.base.MyApplication
import com.lemon.lib_base.data.DataRespository

class CommonViewModel(application: MyApplication, model: DataRespository) :
    BaseViewModel<DataRespository>(application, model) {
}