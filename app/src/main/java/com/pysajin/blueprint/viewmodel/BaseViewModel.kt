package com.pysajin.blueprint.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pysajin.blueprint.model.ItemList
import com.pysajin.blueprint.network.ItemListRepository

abstract class BaseViewModel protected constructor(application: Application) : AndroidViewModel(application){
    enum class ActivityPos(val pos:Int){MAIN(0), MAP(1), MAPREGIST(2)}

    val infoActivity = MutableLiveData<Int>()
    var selectedItem: ItemList? = null

    protected val repository = ItemListRepository(application)
    val itemListBase = arrayOf<ItemList>()

    fun goMainPage(){
        if(infoActivity.value != ActivityPos.MAIN.pos)
            infoActivity.value = ActivityPos.MAIN.pos
    }

    fun goMapPage(selected : ItemList){
        Log.e("TEST","BaseViewModel의 goMapPage")
        if(infoActivity.value != ActivityPos.MAP.pos) {
            selectedItem = selected
            infoActivity.value = ActivityPos.MAP.pos
        }
    }

    fun goMapRegistPage(){
        Log.e("Test", "BaseViewModel의 goMapRegistPage")
        if(infoActivity.value != ActivityPos.MAPREGIST.pos){
            infoActivity.value = ActivityPos.MAPREGIST.pos
        }
    }

}