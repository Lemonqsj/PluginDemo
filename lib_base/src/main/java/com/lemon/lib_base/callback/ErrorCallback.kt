package com.lemon.lib_base.callback

import com.kingja.loadsir.callback.Callback;
import com.lemon.lib_base.R

/**
 * @author Alwyn
 * @Date 2020/11/25
 * @Description
 */
class ErrorCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.common_error_layout
    }
}