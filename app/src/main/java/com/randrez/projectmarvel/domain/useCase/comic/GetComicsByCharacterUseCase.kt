package com.randrez.projectmarvel.domain.useCase.comic

import com.randrez.projectmarvel.R
import com.randrez.projectmarvel.data.source.ResourceResult
import com.randrez.projectmarvel.domain.models.comic.Comic
import com.randrez.projectmarvel.domain.models.comic.toListComic
import com.randrez.projectmarvel.domain.repository.comic.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetComicsByCharacterUseCase constructor(
    private val comicRepository: ComicRepository
) {
    operator fun invoke(characterId: Int): Flow<ResourceResult<List<Comic>>> = flow {
        emit(ResourceResult.Loading())
        val responseComic = comicRepository.getComicsByCharacterId(characterId)
        if (responseComic.code != 200) {
            if (responseComic.status.isNotEmpty()) {
                emit(ResourceResult.Error(responseComic.status))
            } else {
                emit(ResourceResult.ErrorResource(R.string.no_load_comics))
            }
            return@flow
        }
        emit(ResourceResult.Success(responseComic.data.comics.toListComic()))
    }.catch {
        it.printStackTrace()
        emit(ResourceResult.Error(it.message))
    }
}