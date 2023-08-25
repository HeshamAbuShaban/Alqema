package com.alqema.utils

import android.text.Editable

class KotlinUtils private constructor() {

    companion object {
        @Volatile
        private var instance: KotlinUtils? = null
        @JvmStatic
        fun getInstance(): KotlinUtils {
            return instance ?: synchronized(this) {
                KotlinUtils()
            }
        }
    }

    fun Editable?.toInt(): Int? {
        if (this?.isNotEmpty() == true) return this.toString().toInt()
        return null
    }
}