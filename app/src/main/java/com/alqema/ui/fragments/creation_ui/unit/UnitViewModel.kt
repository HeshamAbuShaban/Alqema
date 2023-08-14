package com.alqema.ui.fragments.creation_ui.unit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqema.models.Unit

class UnitViewModel : ViewModel() {
    var unitList = MutableLiveData<List<Unit>>()
        private set

    fun unitList(unitList: List<Unit>) {
        this.unitList.value = unitList
    }
}