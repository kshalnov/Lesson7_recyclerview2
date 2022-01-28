package ru.gb.course1.myapplication.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

// Create Read Update Delete
interface ColorsRepo {
    // Create
    fun addColor(colorEntity: ColorEntity)

    // Read
    fun getColors(): List<ColorEntity>
    val colorsObservable: Observable<List<ColorEntity>>
    val colorsSingle: Single<List<ColorEntity>>

    // Update
    // todo

    // Delete
    fun deleteItem(id: String)
}