package com.lemon.lib_base.callback

import com.kingja.loadsir.callback.Callback;
import com.lemon.lib_base.R

class LoadErrorCallback:Callback() {
    override fun onCreateView(): Int {
      return R.layout.common_error_layout
    }
}