package com.lemon.lib_base.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.kingja.loadsir.core.LoadService
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.lang.ref.WeakReference

/**
 *
 * BaseViewModel:
 * 注意viewmodel的职责，
 *      1存储和处理视图的数据，
 *      2.viewmodel的声明周期要比视图的长，需要注意不能被视图所持有
 *      3. 创建viewmodel需要采用属性委托的方式创建，这样才可以在视图重建的时候恢复视图
 *      4. viewmodel的属性都应该是private的，访问则采用后备属性的方式
 *
 * 1. 是一个观察者
 * 2. 可以感知调用者的声明周期
 * 3. 也是一个被观察者，
 * 4. 可以处理一些通用的数据，如标题，刷新，返回，home按键等事件
 */
open class BaseViewModel<M : BaseModel>(application: MyApplication, val model: M) :
    AndroidViewModel(application), IBaseViewModel, Consumer<Disposable?> {

    lateinit var loadService: LoadService<BaseBean<*>?>
    private lateinit var lifecycle: WeakReference<LifecycleProvider<*>>
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun addSubscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun injectLifecycleProvider(lifecycleProvider: LifecycleProvider<*>) {
        this.lifecycle = WeakReference(lifecycleProvider)
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {}

    override fun onCreate() {}

    override fun onDestroy() {}

    override fun onStart() {}

    override fun onStop() {}

    override fun onResume() {}

    override fun onPause() {}


    override fun onCleared() {
        super.onCleared()

        model.onCleared()
        compositeDisposable.clear()
    }

    override fun accept(t: Disposable?) {
        t?.let { compositeDisposable.add(t) }
    }
}