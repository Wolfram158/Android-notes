package android.learn.jetpackComposePlayground.examples6.di

import android.learn.jetpackComposePlayground.examples6.data.DownloadRepositoryImpl
import android.learn.jetpackComposePlayground.examples6.domain.repository.DownloadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindDownloadRepository(impl: DownloadRepositoryImpl): DownloadRepository
}