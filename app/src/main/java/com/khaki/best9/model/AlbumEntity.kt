package com.khaki.best9.model

import java.time.LocalDate

data class AlbumEntity(
    val id: Long,
    val title: String,
    val artist: String,
    val albumArtUrl: String,
    val releaseMonth: LocalDate,
)
