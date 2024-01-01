package app.example.di

import app.example.App
import app.example.AppCoroutineScope
import app.example.session.UserCoroutineScope
import app.example.session.UserSession
import app.example.ui.MainActivity
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App,
            @BindsInstance appCoroutineScope: AppCoroutineScope,
        ): AppComponent
    }

    fun userComponentFactory(): UserComponent.Factory

    fun inject(app: App)

    fun inject(loginActivity: MainActivity)
}

@SingleIn(UserScope::class)
@MergeSubcomponent(UserScope::class)
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance userSession: UserSession,
            @BindsInstance userCoroutineScope: UserCoroutineScope,
        ): UserComponent
    }
}