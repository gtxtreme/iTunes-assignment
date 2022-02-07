package com.gtxtreme.template.domain.content

data class Content(
    val artistId: Int,
    val thumbnailUrl: String,
    val artistName: String,
    val trackName: String,
    val songName: String,
    val trackId: Int
) {
    override fun equals(other: Any?): Boolean {
        return (this.artistId.plus(trackId)) == (other as Content).artistId.plus(trackId)
    }

    override fun hashCode(): Int {
        var result = artistId
        result = 31 * result + thumbnailUrl.hashCode()
        result = 31 * result + artistName.hashCode()
        result = 31 * result + trackName.hashCode()
        result = 31 * result + songName.hashCode()
        return result
    }
}
