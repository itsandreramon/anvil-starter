package com.example.anvil.di

import com.example.anvil.App
import com.example.anvil.ui.MainActivity

fun inject(activity: MainActivity) {
    (activity.applicationContext as App)
        .appComponent
        .inject(activity)
}