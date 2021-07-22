package com.lemon.ft_main.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lemon.ft_main.R
import com.lemon.ft_main.ui.fragment.HomeFragment
import com.lemon.lib_base.config.AppConstants
import com.lemon.lib_base.data.bean.HomeBannerBean
import com.lemon.lib_base.extension.loadImage
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter


class MyBannerAdapter(
    mData: List<HomeBannerBean?>,
    private val homeFragment: HomeFragment
) :
    BannerAdapter<HomeBannerBean, MyBannerAdapter.ImageTitleHolder>(mData) {

    private var mDiffer: AsyncListDiffer<HomeBannerBean>

    init {
        val diffCallback: DiffUtil.ItemCallback<HomeBannerBean> =
            object : DiffUtil.ItemCallback<HomeBannerBean>() {
                override fun areItemsTheSame(
                    oldItem: HomeBannerBean,
                    newItem: HomeBannerBean
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: HomeBannerBean,
                    newItem: HomeBannerBean
                ): Boolean {
                    return TextUtils.equals(oldItem.imagePath, newItem.imagePath)
                }
            }
        mDiffer = AsyncListDiffer(this, diffCallback)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageTitleHolder {
        return ImageTitleHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_banner_item, parent, false)
        )
    }

    override fun onBindView(
        holder: ImageTitleHolder,
        data: HomeBannerBean,
        position: Int,
        size: Int
    ) {
        holder.imageView.loadImage(data.imagePath)
        holder.title.text = data.title
        holder.imageView.setOnClickListener {
            val bundle = Bundle()
//            bundle.putString(AppConstants.BundleKey.WEB_URL, data.url)
//            homeFragment.viewModel.startContainerActivity(AppConstants.Router.Web.F_WEB,bundle)
        }
    }

    class ImageTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image)
        var title: TextView = view.findViewById(R.id.bannerTitle)
    }

    private fun submitList(data: List<HomeBannerBean>?) {
        mDiffer.submitList(data)
    }

    fun setData(
        banner: Banner<Any, BannerAdapter<*, *>>,
        data: List<HomeBannerBean>?
    ) {
        if (data == mDatas) {
            return
        }
        // 重新赋值新数据到banner
        setDatas(data)
        // 计算差异并赋值新数据
        submitList(data)
        // 设置banner
        banner.apply {
            setCurrentItem(1, false)
            setIndicatorPageChange()
        }
    }

    fun getItem(position: Int): HomeBannerBean? {
        return mDiffer.currentList[position]
    }

}