package ru.gb.course1.myapplication

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.gb.course1.myapplication.data.SnappyDbColorsRepoImpl
import ru.gb.course1.myapplication.domain.ColorsRepo

class App : Application() {
    private val snappyRepo = SnappyDbColorsRepoImpl()

    val colorsRepo: ColorsRepo by lazy {
        SnappyDbColorsRepoImpl().apply { init(this@App) }
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