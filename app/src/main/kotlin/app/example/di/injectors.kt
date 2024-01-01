package app.example.di

import app.example.App
import app.example.ui.MainActivity

fun inject(activity: MainActivity) {
    (activity.applicationContext as App)
        .appComponent
        .inject(activity)
}