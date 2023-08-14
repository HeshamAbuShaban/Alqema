package com.alqema.ui.fragments.creation_ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqema.models.Category

class CategoryViewModel:ViewModel() {
    var categoryList = MutableLiveData<List<Category>>()
        private set

    fun categoryList(categoryList: List<Category>) {
        this.categoryList.value = categoryList
    }

}