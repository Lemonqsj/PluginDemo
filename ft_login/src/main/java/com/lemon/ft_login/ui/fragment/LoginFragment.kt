package com.lemon.ft_login.ui.fragment

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.lemon.ft_login.BR
import com.lemon.ft_login.R
import com.lemon.ft_login.databinding.LoginFragmentLoginBinding
import com.lemon.ft_login.viewmodel.LoginViewModel
import com.lemon.lib_base.base.BaseFragment
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.widget.EditTextMonitor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject


@Route(path = AppConstants.Router.Login.F_LOGIN)
class LoginFragment : BaseFragment<LoginFragmentLoginBinding, LoginViewModel>() {
    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(): Int {
        return R.layout.login_fragment_login
    }

    override fun initParam() {

    }
    override fun initData() {
//        initBtnState()
        LogUtils.d("-----------initData------------")
        viewModel.account.set("000000")
        viewModel.pwd.set("00099999")

    }
    private fun initBtnState() {
        binding.btnLogin.isEnabled = false
        val accountSubject = PublishSubject.create<String>()
        val pwdSubject = PublishSubject.create<String>()
        binding.etAccount.addTextChangedListener(EditTextMonitor(accountSubject))
        binding.etPwd.addTextChangedListener(EditTextMonitor(pwdSubject))
        viewModel.addSubscribe(
            Observable.combineLatest(
            accountSubject,
            pwdSubject,
            { account: String, pwd: String ->
                account.isNotBlank() && pwd.isNotBlank()
            }).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.btnLogin.isEnabled = it
                binding.btnLogin.setTextColor(
                    if (it) Color.parseColor("#000000") else Color.parseColor("#ffffff")
                )
                binding.btnLogin.setBackgroundResource(if (it) R.drawable.shape_round_white else R.drawable.gray_btn_corner_10dp)
            })
    }

}