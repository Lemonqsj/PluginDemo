package com.lemon.ft_login.viewmodel

import androidx.databinding.ObservableField
import com.lemon.lib_base.base.BaseViewModel
import com.lemon.lib_base.base.MyApplication
import com.lemon.lib_base.data.DataRespository

class LoginViewModel(application: MyApplication, model: DataRespository) :
    BaseViewModel<DataRespository>(application, model) {

    var account = ObservableField("")
    var pwd = ObservableField("")
}