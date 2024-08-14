package android.learn.coroutinesPlayground.flow.example3DI.di

import android.learn.coroutinesPlayground.flow.example3DI.presentation.StringViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StringViewModel::class)
    fun bindViewModel(viewModel: StringViewModel): ViewModel

}