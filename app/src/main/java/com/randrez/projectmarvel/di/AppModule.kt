package com.randrez.projectmarvel.di

import android.content.Context
import com.randrez.projectmarvel.data.remote.MarvelApi
import com.randrez.projectmarvel.domain.repository.character.CharacterRepository
import com.randrez.projectmarvel.domain.repository.character.CharacterRepositoryImpl
import com.randrez.projectmarvel.domain.repository.comic.ComicRepository
import com.randrez.projectmarvel.domain.repository.comic.ComicRepositoryImpl
import com.randrez.projectmarvel.domain.repository.dataStore.DataStoreRepository
import com.randrez.projectmarvel.domain.repository.dataStore.DataStoreRepositoryImpl
import com.randrez.projectmarvel.domain.useCase.GenerateMD5UseCase
import com.randrez.projectmarvel.domain.useCase.character.GetCharactersUseCase
import com.randrez.projectmarvel.domain.useCase.comic.GetComicsByCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideCharacterRepository(marvelApi: MarvelApi): CharacterRepository =
        CharacterRepositoryImpl(marvelApi = marvelApi)

    @Singleton
    @Provides
    fun provideComicRepository(marvelApi: MarvelApi): ComicRepository =
        ComicRepositoryImpl(marvelApi = marvelApi)

    @Singleton
    @Provides
    fun provideGenerateMD5UseCase(dataStoreRepository: DataStoreRepository): GenerateMD5UseCase =
        GenerateMD5UseCase(dataStoreRepository = dataStoreRepository)

    @Singleton
    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetCharactersUseCase =
        GetCharactersUseCase(characterRepository = characterRepository)

    @Singleton
    @Provides
    fun provideGetComicsByCharacterUseCase(comicRepository: ComicRepository):GetComicsByCharacterUseCase =
        GetComicsByCharacterUseCase(comicRepository = comicRepository)
}