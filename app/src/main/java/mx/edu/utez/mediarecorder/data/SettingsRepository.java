
import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val VOLUME_KEY = floatPreferencesKey("volume_level")
        const val DEFAULT_VOLUME = 0.5f
    }

    val userVolume: Flow<Float> = dataStore.data.map { prefs ->
            prefs[VOLUME_KEY] ?: DEFAULT_VOLUME
    }

    suspend fun saveVolume(volume: Float) {
        dataStore.edit { prefs ->
                prefs[VOLUME_KEY] = volume.coerceIn(0.0f, 1.0f)
        }
    }
}
