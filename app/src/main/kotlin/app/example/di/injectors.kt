package app.example.di

import app.example.App
import app.example.navigation.home.HomeRouting
import app.example.navigation.login.LoginRouting

fun inject(homeRouting: HomeRouting) {
    (homeRouting.applicationContext as App)
        .userSessionManager
        .userComponent!!
        .inject(homeRouting)
}

fun inject(loginRouting: LoginRouting) {
    (loginRouting.applicationContext as App)
        .appComponent
        .inject(loginRouting)
}
