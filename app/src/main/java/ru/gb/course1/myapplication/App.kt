package ru.gb.course1.myapplication

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.gb.course1.myapplication.data.SimpleColorsRepoImpl
import ru.gb.course1.myapplication.domain.ColorsRepo


class App : Application() {
    val colorsRepo: ColorsRepo by lazy { SimpleColorsRepoImpl() }

    override fun onCreate() {
        super.onCreate()
        foo(this)
    }

    override fun onTerminate() {
        super.onTerminate()
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

    // Single - onNext(1 раз) -> onComplete / onError
    // MayBe - onNext(0 или 1 раз) -> onComplete / onError
    // Completable - onComplete / onError

    val disposable = observable // livedata
        .filter {
            it % 2 == 0
        }
        .map {
            it.toString()
        }
        .take(10)
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            when (it) {
                "1" -> "Один"
                "2" -> "Два"
                else -> it
            }
        }
        .subscribeOn(Schedulers.computation())
        .subscribeBy( // observe
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