package com.lemon.ft_login.viewmodel

import androidx.databinding.ObservableField
import com.blankj.utilcode.util.LogUtils
import com.lemon.lib_base.base.BaseViewModel
import com.lemon.lib_base.base.MyApplication
import com.lemon.lib_base.binding.command.BindingAction
import com.lemon.lib_base.binding.command.BindingCommand
import com.lemon.lib_base.binding.command.BindingConsumer
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.data.DataRespository
import com.lemon.lib_base.route.RouteCenter

class LoginViewModel(application: MyApplication, model: DataRespository) :
    BaseViewModel<DataRespository>(application, model) {

    var account = ObservableField("")
    var pwd = ObservableField("")

    val onAccountChangeCommand: BindingCommand<String> = BindingCommand(BindingConsumer {
        account.set(it)
        LogUtils.d("----账号---------"+it)
    })

    val onPwdChangeCommand: BindingCommand<String> = BindingCommand(BindingConsumer {
        pwd.set(it)
        LogUtils.d("----密码---------"+it)
    })

    var btnLoginClick: BindingCommand<Any> = BindingCommand(BindingAction {
        loginByPwd()
    })

    private fun loginByPwd() {
        LogUtils.d("---------------------------")
        RouteCenter.navigate(AppConstants.Router.Main.A_MAIN)
//        AppManager.instance.finishAllActivity()
    }

}