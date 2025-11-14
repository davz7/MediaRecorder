import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.ejemplo.mediaapp.data.MediaItem;

import kotlin.jvm.Volatile;
import mx.edu.utez.mediarecorder.data.MediaDao;

@Database(entities = [MediaItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mediaDao():MediaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context:Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "media_app_database"
                )
                .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
