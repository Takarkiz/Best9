package com.khaki.best9.repository

import com.khaki.best9.model.AlbumEntity

interface AlbumSearchRepository {

    suspend fun searchAlbums(query: String): List<AlbumEntity>

    suspend fun albumDetail(id: Long): AlbumEntity
}
