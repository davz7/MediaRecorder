package mx.edu.utez.mediarecorder.data;

import kotlinx.coroutines.flow.Flow

class MediaRepository(private val mediaDao: MediaDao) {

    fun getAllAudio(): Flow<List<MediaItem>> {
        return mediaDao.getMediaByType(MediaType.AUDIO)
    }

    fun getAllImages(): Flow<List<MediaItem>> {
        return mediaDao.getMediaByType(MediaType.IMAGE)
    }

    fun getAllVideos(): Flow<List<MediaItem>> {
        return mediaDao.getMediaByType(MediaType.VIDEO)
    }

    suspend fun insertMedia(item: MediaItem) {
        mediaDao.insertMedia(item)
    }
}
