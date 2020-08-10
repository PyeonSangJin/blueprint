package com.pysajin.blueprint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pysajin.blueprint.model.ItemList
import com.pysajin.blueprint.network.ItemListRepository

abstract class BaseViewModel protected constructor(application: Application) : AndroidViewModel(application){
    enum class ActivityPos(val pos:Int){MAIN(0), MAP(1)}

    val infoActivity = MutableLiveData<Int>()

    protected val repository = ItemListRepository(application)
    val itemListBase = ArrayList<ItemList>()

    fun goMainPage(){
        if(infoActivity.value != ActivityPos.MAIN.pos)
            infoActivity.value = ActivityPos.MAIN.pos
    }

    fun goMapPage(){
        if(infoActivity.value != ActivityPos.MAP.pos)
            infoActivity.value = ActivityPos.MAP.pos
    }

}