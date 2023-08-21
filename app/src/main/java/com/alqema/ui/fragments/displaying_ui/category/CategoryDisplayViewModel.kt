package com.alqema.ui.fragments.displaying_ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alqema.app_system.AppController
import com.alqema.database.repo.AlqemaRepository
import com.alqema.database.local_db.models.Category

class CategoryDisplayViewModel : ViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = _categoryList

    private fun categoryList(categoryList: LiveData<List<Category>>) {
        this._categoryList.value = categoryList.value
    }

    fun fetchData() {
        val repo = AlqemaRepository(AppController.getInstance())
        categoryList(repo.observeCategories())
    }

}