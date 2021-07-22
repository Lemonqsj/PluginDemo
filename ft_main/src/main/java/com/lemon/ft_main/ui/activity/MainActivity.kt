package com.lemon.ft_main.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.lemon.ft_main.BR
import com.lemon.ft_main.R
import com.lemon.ft_main.databinding.MainActivityMainBinding
import com.lemon.ft_main.viewmodel.MainViewModel
import com.lemon.lib_base.adapter.ViewPagerFmAdapter
import com.lemon.lib_base.base.BaseActivity
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.route.RouteCenter
import me.yokeyword.fragmentation.SupportFragment

@Route(path = AppConstants.Router.Main.A_MAIN)
class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>() {

    override fun initViewModelId(): Int {
        return BR.viewmodel
    }

    override fun initLayoutContentView(): Int {
        return R.layout.main_activity_main
    }

    override fun initParam() {

    }

    override fun initData() {
        initBottomBar()
        initViewPager()
    }

    override fun initViewObservable() {

    }

    private fun initViewPager() {
        // 设置不可滑动
        binding.viewPager2.isUserInputEnabled = false
        val homeFragment = RouteCenter.navigate(AppConstants.Router.Main.F_HOME) as SupportFragment
        val squareFragment =
            RouteCenter.navigate(AppConstants.Router.Main.F_HOME) as SupportFragment
        val projectFragment =
            RouteCenter.navigate(AppConstants.Router.Main.F_HOME) as SupportFragment
        val userFragment = RouteCenter.navigate(AppConstants.Router.Main.F_HOME) as SupportFragment
        val fragments = arrayListOf(homeFragment, squareFragment, projectFragment, userFragment)
        binding.viewPager2.apply {
            adapter = ViewPagerFmAdapter(supportFragmentManager, lifecycle, fragments)
            offscreenPageLimit = fragments.size
        }
    }

    private fun initBottomBar() {
        binding.bottomBar.apply {
            setMode(BottomNavigationBar.MODE_FIXED)
            setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_home_on,
                    getString(R.string.main_tab_home)
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_home_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_square_on,
                    getString(R.string.main_tab_square)
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_square_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_project_on,
                    getString(R.string.main_tab_project)
                )
                    .setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_project_off)
            )
            addItem(
                BottomNavigationItem(
                    R.drawable.ic_me_on,
                    getString(R.string.main_tab_me)
                ).setActiveColorResource(R.color.md_theme_red)
                    .setInactiveIconResource(R.drawable.ic_me_off)
            )
            setFirstSelectedPosition(0)
            initialise()
        }
    }
}