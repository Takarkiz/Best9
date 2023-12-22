package com.khaki.best9.repositoryImpl

import com.khaki.best9.model.AlbumEntity
import com.khaki.best9.repository.AlbumSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.random.Random

//class AlbumSearchRepositoryImpl(
//    private val api: SpotifyApi,
//) : AlbumSearchRepository {
//
//    override suspend fun searchAlbums(query: String): List<AlbumEntity> {
//        val response = api.searchAlbums(query)
//        return response.albums.map {
//            AlbumEntity(
//                id = it.id,
//                title = it.title,
//                artist = it.artist,
//                albumArtUrl = it.albumArtUrl,
//                releaseMonth = it.releaseMonth,
//            )
//        }
//    }
//
//    override suspend fun albumDetail(id: Long): AlbumEntity {
//        val response = api.albumDetail(id)
//        return AlbumEntity(
//            id = response.id,
//            title = response.title,
//            artist = response.artist,
//            albumArtUrl = response.albumArtUrl,
//            releaseMonth = response.releaseMonth,
//        )
//    }
//}

class DummyAlbumSearchRepositoryImpl : AlbumSearchRepository {

    override suspend fun searchAlbums(query: String): List<AlbumEntity> =
        withContext(Dispatchers.IO) {
            delay(3000)
            listOf(
                AlbumEntity(
                    id = 1,
                    title = "title",
                    artist = "artist",
                    albumArtUrl = "https://via.placeholder.com/150",
                    releaseMonth = LocalDate.now(),
                ),
                AlbumEntity(
                    id = 2,
                    title = "So Long Title Album Name, Too Long Album Name",
                    artist = "artist",
                    albumArtUrl = "https://via.placeholder.com/150",
                    releaseMonth = LocalDate.now().minusWeeks(20),
                ),
                AlbumEntity(
                    id = 3,
                    title = "title",
                    artist = "So Long Artist Name & So Long Artist Name & Too Long Artist Name",
                    albumArtUrl = "https://via.placeholder.com/150",
                    releaseMonth = LocalDate.now().minusWeeks(10),
                ),
                AlbumEntity(
                    id = 4,
                    title = "title",
                    artist = "artist",
                    albumArtUrl = "https://via.placeholder.com/150",
                    releaseMonth = LocalDate.now().minusWeeks(5),
                ),
            ).take(Random.nextInt(3))
        }

    override suspend fun albumDetail(id: Long): AlbumEntity = withContext(Dispatchers.IO) {
        delay(3000)
        AlbumEntity(
            id = 1,
            title = "title",
            artist = "artist",
            albumArtUrl = "https://via.placeholder.com/150",
            releaseMonth = LocalDate.now(),
        )
    }
}
