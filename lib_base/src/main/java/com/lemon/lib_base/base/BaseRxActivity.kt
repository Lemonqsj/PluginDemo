package com.lemon.lib_base.base

import android.os.Bundle
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.LifecycleTransformer
import com.trello.rxlifecycle3.RxLifecycle
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity

/**
 * BaseRxActivity 的作用：
 * 1. 可以侧滑的view
 * 2. 是一个lifecycyle监听者
 *
 */
abstract class BaseRxActivity:SwipeBackActivity(),LifecycleProvider<ActivityEvent> {


    /**
     *
     * subject 既是一个obserable又是一个observer
     *
     * 其中BehaviorSubject 作为一个observerable 只会发送订阅之前的一个数据和订阅后的数据，平时可以作为缓存使用
     *
     *
     */
    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()
    override fun lifecycle(): Observable<ActivityEvent> {
      return lifecycleSubject.hide()
    }

    //指定在某一个声明周期解除绑定，也就在某一个声明周期再不监听了
    override fun <T : Any?> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
      return RxLifecycle.bindUntilEvent(lifecycleSubject,event)
    }

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleSubject.onNext(ActivityEvent.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
    }
}