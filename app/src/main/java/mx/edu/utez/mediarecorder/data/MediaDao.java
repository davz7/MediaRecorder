package mx.edu.utez.mediarecorder.data;

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(item: MediaItem)

    @Query("SELECT * FROM media_items WHERE type = :type ORDER BY date DESC")
    fun getMediaByType(type: MediaType): Flow<List<MediaItem>>
}
