package com.lemon.ft_login.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.lemon.ft_login.BR
import com.lemon.ft_login.R
import com.lemon.ft_login.databinding.LoginFragmentLoginBinding
import com.lemon.ft_login.viewmodel.LoginViewModel
import com.lemon.lib_base.base.BaseFragment
import com.lemon.lib_base.config.AppConstants


@Route(path = AppConstants.Router.Login.F_LOGIN)
class LoginFragment : BaseFragment<LoginFragmentLoginBinding, LoginViewModel>() {
    override fun initVariableId(): Int {
        return BR.viewmodel
    }

    override fun initContentView(): Int {
        return R.layout.login_fragment_login
    }
}