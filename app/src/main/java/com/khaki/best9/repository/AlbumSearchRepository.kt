package com.khaki.best9.repository

import com.khaki.best9.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

interface AlbumSearchRepository {

    suspend fun searchAlbums(query: String): Flow<List<AlbumEntity>>

    suspend fun albumDetail(id: Long): Flow<AlbumEntity>
}
