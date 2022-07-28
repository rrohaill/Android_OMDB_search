package io.rohail.androidomdbsearch.base.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rohail.androidomdbsearch.base.api.OmdbApi
import io.rohail.androidomdbsearch.base.database.FavouriteDao
import io.rohail.androidomdbsearch.base.database.MyDB
import io.rohail.androidomdbsearch.base.database.MyDatabase
import io.rohail.androidomdbsearch.ui.search.data.OMDBDataSource
import io.rohail.androidomdbsearch.ui.search.data.OMDBDataSourceImpl
import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import io.rohail.androidomdbsearch.ui.search.data.OMDBRepositoryImpl
import io.rohail.androidomdbsearch.ui.search.usecase.*
import io.rohail.metaweatherapp.api.ApiFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOMDBRepository(@ApplicationContext appContext: Context): OMDBRepository =
        OMDBRepositoryImpl(
            omdbDataSource =
            provideOMDBDataSource(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideOMDBDataSource(@ApplicationContext appContext: Context): OMDBDataSource =
        OMDBDataSourceImpl(
            service = provideApi(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext appContext: Context): OmdbApi =
        ApiFactory.getApi(context = appContext)

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext appContext: Context): MyDB =
        MyDatabase.getInstance(context = appContext)

    @Provides
    @Singleton
    fun provideFavouriteDao(@ApplicationContext appContext: Context): FavouriteDao =
        provideDB(appContext = appContext).favouriteDao()

}

//Usecases
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindSearchDatabaseUseCase(impl: SearchDatabaseUseCaseImpl): SearchDatabaseUseCase

    @Binds
    @Singleton
    abstract fun bindGetSearchResultUseCase(impl: GetSearchResultUseCaseImpl): GetSearchResultUseCase

    @Binds
    @Singleton
    abstract fun bindFetchDetailUseCase(impl: FetchDetailUseCaseImpl): FetchDetailUseCase

    @Binds
    @Singleton
    abstract fun bindGetDetailResultUseCase(impl: GetDetailResultUseCaseImpl): GetDetailResultUseCase

}
