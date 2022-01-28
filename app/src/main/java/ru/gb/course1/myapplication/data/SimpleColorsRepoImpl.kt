package ru.gb.course1.myapplication.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.gb.course1.myapplication.domain.ColorEntity
import ru.gb.course1.myapplication.domain.ColorsRepo
import ru.gb.course1.myapplication.util.Utils
import java.util.*
import java.util.concurrent.TimeUnit

class SimpleColorsRepoImpl : ColorsRepo {
    private val data: MutableList<ColorEntity> = ArrayList()

    private val dataSubject = BehaviorSubject.createDefault<List<ColorEntity>>(data)

    init {
        synchronized(data) {
            data.add(0, Utils.randomColor())
            data.add(0, Utils.randomColor())
            data.add(0, Utils.randomColor())
        }
        Thread {
            synchronized(data) {
                dataSubject.onNext(data)
            }
        }.start()
    }

    // изменяющие
    override fun addColor(colorEntity: ColorEntity) {
        synchronized(data) {
            data.add(0, colorEntity)
            dataSubject.onNext(data)
        }

    }

    override fun getColors(): List<ColorEntity> {
        synchronized(data) {
            return ArrayList(data)
        }
    }

    // изменяющие
    override fun deleteItem(id: String) {
        Thread {
            Thread.sleep(2_000)
            synchronized(data) {
                for (i in data.indices) {
                    if (data[i].id == id) {
                        data.removeAt(i)
                        dataSubject.onNext(data)
                        break
                    }
                }
            }
        }.start()
    }

    override val colorsObservable: Observable<List<ColorEntity>>
        get() = dataSubject

    override val colorsSingle: Single<List<ColorEntity>>
        get() = Single.timer(3, TimeUnit.SECONDS).map { data }
}