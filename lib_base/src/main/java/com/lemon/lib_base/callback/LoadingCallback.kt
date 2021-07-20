package com.lemon.lib_base.callback

import com.kingja.loadsir.callback.Callback
import com.lemon.lib_base.R

class LoadingCallback:Callback() {
    override fun onCreateView(): Int {
      return R.layout.common_loading_layout
    }
}