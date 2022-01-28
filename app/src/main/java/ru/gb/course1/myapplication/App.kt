package ru.gb.course1.myapplication

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.gb.course1.myapplication.data.SnappyDbColorsRepoImpl
import ru.gb.course1.myapplication.domain.ColorsRepo
import java.util.concurrent.TimeUnit


class App : Application() {
    private val snappyRepo = SnappyDbColorsRepoImpl()

    val colorsRepo: ColorsRepo by lazy {
        SnappyDbColorsRepoImpl().apply { init(this@App) }
    }

    override fun onCreate() {
        super.onCreate()
        foo(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        snappyRepo.destroy()
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app


// WARNING псевдо код

fun foo(context: Context) {
    val data = listOf(1, 2, 3, 4, 5)
    val observable = Observable.fromIterable(data)

    val disposable = observable
        .map {
            it.toString()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            when (it) {
                "1" -> "Один"
                "2" -> "Два"
                else -> it
            }
        }
        .subscribeOn(Schedulers.computation())
        .subscribeBy(
            onError = {
                Toast.makeText(context, "doOnError $it", Toast.LENGTH_SHORT).show()
            },
            onNext = {
                Toast.makeText(context, "doOnNext $it", Toast.LENGTH_SHORT).show()
            },
            onComplete = {
                Toast.makeText(context, "doOnComplete", Toast.LENGTH_SHORT).show()
            }
        )
    // todo onDestroy - disposable.dispose()

}